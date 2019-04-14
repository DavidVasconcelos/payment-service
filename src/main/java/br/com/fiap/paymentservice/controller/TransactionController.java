package br.com.fiap.paymentservice.controller;

import br.com.fiap.paymentservice.model.Transaction;
import br.com.fiap.paymentservice.repository.TransactionRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/transactions")
@Api(value = "Payment", description = "Payment Service REST API")
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @ApiOperation(httpMethod = "GET", value = "Método get para buscar transação filtrando por id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna um Order com uma mensagem de sucesso", response = Transaction.class)})
    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {

        final Transaction transaction = repository.getById(id);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "Post", value = "Método post inserir um transação")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna o path para pesquisa")})
    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction) {

        final Transaction savedTransaction = repository.save(transaction);

        URI location = getUri(savedTransaction);

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(httpMethod = "Put", value = "Método put para alterar um transação por id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna uma mensagem de sucesso")})
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable("id") Long id, @RequestBody Transaction transaction) {

        repository.update(id, transaction);

        return new ResponseEntity(HttpStatus.OK);
    }

    @ApiOperation(httpMethod = "Delete", value = "Método delete para apagar um transação por id")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Retorna uma mensagem de sucesso")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> delete(@PathVariable("id") Long id) {

        final Transaction savedTransaction = repository.getById(id);

        repository.delete(savedTransaction);

        return new ResponseEntity(HttpStatus.OK);
    }

    private URI getUri(Transaction savedTransaction) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedTransaction.getId()).toUri();
    }

}
