package br.com.fiap.paymentservice.controller;

import br.com.fiap.paymentservice.model.Transaction;
import br.com.fiap.paymentservice.repository.TransactionRepository;
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
public class TransactionController {

    @Autowired
    private TransactionRepository repository;

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable("id") Long id) {

        final Transaction transaction = repository.getById(id);

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> save(@RequestBody Transaction transaction) {

        final Transaction savedTransaction = repository.save(transaction);

        URI location = getUri(savedTransaction);

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Transaction> update(@PathVariable("id") Long id, @RequestBody Transaction transaction) {

        repository.update(id, transaction);

        return new ResponseEntity(HttpStatus.OK);
    }

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
