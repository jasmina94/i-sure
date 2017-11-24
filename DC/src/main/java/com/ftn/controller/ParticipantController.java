package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.ParticipantDTO;
import com.ftn.service.ParticipantService;
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
@RequestMapping("/api/participants")
public class ParticipantController {

    private final ParticipantService participantService;

    @Autowired
    public ParticipantController(ParticipantService participantService){
        this.participantService = participantService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(participantService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody ParticipantDTO participantDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(participantService.create(participantDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody ParticipantDTO participantDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(participantService.update(id, participantDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
    	participantService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(participantService.findById(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/customer/{id}")
    public ResponseEntity findByCustomer(@PathVariable Long id){
        return new ResponseEntity<>(participantService.findByCustomerId(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/policy/{id}")
    public ResponseEntity findByPolicy(@PathVariable Long id){
        return new ResponseEntity<>(participantService.findByPolicyId(id), HttpStatus.OK);
    }
}
