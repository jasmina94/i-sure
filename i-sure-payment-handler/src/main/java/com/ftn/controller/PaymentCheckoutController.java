package com.ftn.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ftn.model.dto.PaymentDTO;
import com.ftn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.ftn.exception.BadRequestException;
import com.ftn.model.dto.PaymentCheckoutDTO;
import com.ftn.model.dto.TransactionDTO;
import com.ftn.model.dto.TransactionStatus;
import com.ftn.service.TransactionService;

@Controller
@RequestMapping("/checkout")
public class PaymentCheckoutController {

    private RestTemplate restTemplate = new RestTemplate();

    private final TransactionService transactionService;

    private final PaymentService paymentService;

    @Autowired
    public PaymentCheckoutController(TransactionService transactionService, PaymentService paymentService) {
        this.transactionService = transactionService;
        this.paymentService = paymentService;
    }

    @PostMapping(value = "success")
    public ResponseEntity successPay(@RequestBody PaymentCheckoutDTO paymentCheckoutDTO) {
        String paymentId = paymentCheckoutDTO.getPaymentId();
        TransactionDTO transactionDTO = transactionService.findByPaymentId(paymentId);
        transactionDTO.setStatus(TransactionStatus.BOOKED);
        if (paymentCheckoutDTO.getAcquirerOrderId() != 0 && paymentCheckoutDTO.getAcquirerTimestamp() != null) {
            transactionDTO.setAcquirerOrderId(paymentCheckoutDTO.getAcquirerOrderId());
            transactionDTO.setAcquirerTimestamp(paymentCheckoutDTO.getAcquirerTimestamp());
        }
        transactionService.update(transactionDTO.getId(), transactionDTO);
        System.out.println("Usao u success");
        return new ResponseEntity(paymentCheckoutDTO, HttpStatus.OK);
    }

    @PostMapping(value = "cancel")
    public ResponseEntity cancelPay(@RequestBody PaymentCheckoutDTO paymentCheckoutDTO) {
        String paymentId = paymentCheckoutDTO.getPaymentId();
        TransactionDTO transactionDTO = transactionService.findByPaymentId(paymentId);
        transactionDTO.setStatus(TransactionStatus.REVERSED);
        transactionService.update(transactionDTO.getId(), transactionDTO);

        System.out.println("Usao u cancel");

        return new ResponseEntity(paymentCheckoutDTO, HttpStatus.NO_CONTENT);
    }
}
