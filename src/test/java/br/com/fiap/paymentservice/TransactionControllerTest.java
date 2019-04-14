package br.com.fiap.paymentservice;

import br.com.fiap.paymentservice.controller.TransactionController;
import br.com.fiap.paymentservice.model.*;
import br.com.fiap.paymentservice.repository.TransactionRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TransactionRepository repository;

    @Test
    public void notFoundTransaction() throws Exception {
        final String id = "1";
        mvc.perform(get("/transaction/" + 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void transactionFounded() throws Exception {
        final Transaction transaction = getTransaction();
        when(this.repository.getById(1L)).thenReturn(transaction);
        mvc.perform(get("/transactions/" + transaction.getId())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(response -> {
                    String json = response.getResponse().getContentAsString();
                    Transaction transactionFounded = new ObjectMapper().readValue(json, Transaction.class);
                    Assertions.assertThat(transaction).isEqualToComparingFieldByField(transactionFounded);
                });
    }


    @Test
    public void insertTransaction() throws Exception {
        final Transaction transaction = getTransaction();
        when(this.repository.getById(1L)).thenReturn(new Transaction());

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        String jsonInString = mapper.writeValueAsString(transaction);

        mvc.perform(post("/transactions")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonInString))
                .andExpect(status().isCreated());
    }


    private Transaction getTransaction() {
        return new Transaction(1L,
                "5488888888887192", "12/23",
                new BigDecimal(10.13).setScale(2, BigDecimal.ROUND_HALF_UP)
                , Brand.VISA);
    }


}