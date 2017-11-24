package com.ftn.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.HomeInsuranceDTO;
import com.ftn.service.HomeInsuranceService;
import com.ftn.service.implementation.HomeInsuranceServiceImpl;

@Controller
@RequestMapping("/homeInsurance")
public class HomeInsuranceController {

	private final HomeInsuranceService homeInsuranceService;

	@Autowired
	public HomeInsuranceController(HomeInsuranceServiceImpl homeInsuranceService) {
		this.homeInsuranceService = homeInsuranceService;
	}

	@GetMapping
	public ResponseEntity read() {
		return new ResponseEntity<>(homeInsuranceService.readAll(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity create(@Valid @RequestBody HomeInsuranceDTO homeInsuranceDTO, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			throw new BadRequestException();
		return new ResponseEntity<>(homeInsuranceService.create(homeInsuranceDTO), HttpStatus.OK);
	}

	@PatchMapping(value = "/{id}")
	public ResponseEntity update(@PathVariable Long id, @Valid @RequestBody HomeInsuranceDTO homeInsuranceDTO,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			throw new BadRequestException();
		}

		HomeInsuranceDTO hiDTO;

		try {
			hiDTO = homeInsuranceService.update(id, homeInsuranceDTO);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hiDTO, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity delete(@PathVariable Long id) {

		try {
			homeInsuranceService.delete(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity findById(@PathVariable Long id) {
		HomeInsuranceDTO hiDTO;
		try {
			hiDTO = homeInsuranceService.findById(id);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(hiDTO, HttpStatus.OK);
	}

	@GetMapping(value = "/byUcn/{ucn}")
	public ResponseEntity findByUcn(@PathVariable String ucn) {
	
		return new ResponseEntity<>(homeInsuranceService.findByUcn(ucn), HttpStatus.OK);
	}

}
