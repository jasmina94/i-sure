package com.ftn.controller;

import com.ftn.model.database.Card;
import com.ftn.repository.AccountRepository;
import com.ftn.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

/**
 * Created by Jasmina on 04/12/2017.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private final CardService testService;

    private final AccountRepository accountRepository;

    @Autowired
    public TestController(CardService testService, AccountRepository accountRepository){
        this.testService = testService;
        this.accountRepository = accountRepository;
    }

    @Transactional
    @GetMapping(value = "/account")
    public ResponseEntity accounts() {
        return new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(testService.read(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody Card card, BindingResult bindingResult) {
        return new ResponseEntity<>(testService.create(card), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody Card card, BindingResult bindingResult) {
        return new ResponseEntity<>(testService.update(id, card), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        testService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
