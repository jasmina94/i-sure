package com.ftn.endpoint;

import com.ftn.model.dto.mt102.GetMt102Request;
import com.ftn.model.dto.mt102.GetMt102Response;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.ClearingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by Jasmina on 24/06/2017.
 */
@Endpoint
public class Mt102Endpoint {

    public static final String NAMESPACE_URI = "http://www.ftn.com/mt102";

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private ClearingService clearingService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMt102Request")
    @ResponsePayload
    public GetMt102Response mt102(@RequestPayload GetMt102Request request) {
        final GetMt102Response response = new GetMt102Response();
        clearingService.save(request.getMt102());
        response.setMt102("Ok");
        return response;
    }
}
