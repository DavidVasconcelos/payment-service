package br.com.fiap.paymentservice.exception;

public class TransactionNotFoundException extends RuntimeException {

	public TransactionNotFoundException(final String message) {
		super(message);
	}

}