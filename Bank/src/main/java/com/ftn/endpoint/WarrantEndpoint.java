package com.ftn.endpoint;

import com.ftn.model.dto.warrant.GetWarrantRequest;
import com.ftn.model.dto.warrant.GetWarrantResponse;
import com.ftn.model.dto.warrant.Warrant;
import com.ftn.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by Jasmina on 24/06/2017.
 */
@Endpoint
public class WarrantEndpoint {

    public static final String NAMESPACE_URI = "http://www.ftn.com/warrant";

    @Autowired
    private PaymentService paymentService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getWarrantRequest")
    @ResponsePayload
    public GetWarrantResponse warrant(@RequestPayload GetWarrantRequest request) {
        final GetWarrantResponse response = new GetWarrantResponse();
        Warrant warrant = request.getWarrant();
        paymentService.process(warrant);
        response.setWarrant("Ok");
        return response;
    }
}