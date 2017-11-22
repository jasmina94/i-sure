package com.ftn.controller;

import com.ftn.exception.BadRequestException;
import com.ftn.model.InsurancePolicy;
import com.ftn.model.dto.InsurancePolicyDTO;
import com.ftn.service.InsurancePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by Jasmina on 22/11/2017.
 */
@RestController
@RequestMapping("/api/insurancePolicy")
public class InsurancePolicyController {

    private final InsurancePolicyService insurancePolicyService;

    @Autowired
    public InsurancePolicyController(InsurancePolicyService insurancePolicyService){
        this.insurancePolicyService = insurancePolicyService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(insurancePolicyService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(insurancePolicyService.create(insurancePolicyDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody InsurancePolicyDTO insurancePolicyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(insurancePolicyService.update(id, insurancePolicyDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        insurancePolicyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(insurancePolicyService.findById(id), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/{date}")
    public ResponseEntity findByDateOfIssue(@PathVariable Date date){
        return new ResponseEntity<>(insurancePolicyService.findByDateOfIssue(date), HttpStatus.OK);
    }
}
