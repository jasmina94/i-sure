package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.InsuredDTO;
import com.ftn.service.InsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Jasmina on 22/11/2017.
 */
@RestController
@RequestMapping("/api/insured")
public class InsuredController {

    private final InsuredService insuredService;

    @Autowired
    public InsuredController(InsuredService insuredService){
        this.insuredService = insuredService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(insuredService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InsuredDTO insuredDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(insuredService.create(insuredDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InsuredDTO insuredDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(insuredService.update(id, insuredDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        insuredService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(insuredService.findById(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity findByCustomer(@PathVariable Long id){
        return new ResponseEntity<>(insuredService.findByCustomerId(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/policy/{id}")
    public ResponseEntity findByPolicy(@PathVariable Long id){
        return new ResponseEntity<>(insuredService.findByPolicyId(id), HttpStatus.OK);
    }
}
