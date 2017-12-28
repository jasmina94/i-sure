package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.CustomerDTO;
import com.ftn.model.dto.PricelistDTO;
import com.ftn.service.PricelistService;

@RestController
@RequestMapping("/pricelists")
public class PricelistController {
	
    private final PricelistService pricelistService;

    @Autowired
    public PricelistController(PricelistService pricelistService){
        this.pricelistService = pricelistService;
    }

    @Transactional
    @GetMapping
    public ResponseEntity read() {
        return new ResponseEntity<>(pricelistService.readAll(), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PricelistDTO pricelistDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new BadRequestException();
        return new ResponseEntity<>(pricelistService.create(pricelistDTO), HttpStatus.OK);
    }

    @Transactional
    @PatchMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody PricelistDTO pricelistDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException();
        }
        return new ResponseEntity<>(pricelistService.update(id, pricelistDTO), HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        pricelistService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Transactional
    @GetMapping(value = "/{id}")
    public ResponseEntity findById(@PathVariable Long id){
        return new ResponseEntity<>(pricelistService.findById(id), HttpStatus.OK);
    }
    
    
    @Transactional
    @GetMapping(value = "/currentlyActive")
    public ResponseEntity findcurrentlyActive(){
    	System.out.println("Currently active controller");
    	//pricelistService.findcurrentlyActive();
    	
        return new ResponseEntity<>(pricelistService.findcurrentlyActive(), HttpStatus.OK);
    }
}
