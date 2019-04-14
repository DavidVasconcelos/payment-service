package br.com.fiap.paymentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_EMPTY)
public class Transaction {

    @ApiModelProperty(notes = "The database generated Order ID")
    private Long id;

    @ApiModelProperty( notes = "Card number", required = true)
    private String cardNumber;

    @ApiModelProperty( notes = "Expiration Date", required = true)
    private String expirationDate;

    @ApiModelProperty( notes = "Amount", required = true)
    private BigDecimal amount;

    @ApiModelProperty( notes = "Brand", required = true)
    private Brand brand;


}
