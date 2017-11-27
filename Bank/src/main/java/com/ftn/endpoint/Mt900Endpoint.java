package com.ftn.endpoint;

import com.ftn.model.dto.mt900.GetMt900Request;
import com.ftn.model.dto.mt900.GetMt900Response;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.ClearingService;
import com.ftn.service.RTGSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Cre
 * ated by Jasmina on 24/06/2017.
 */
@Endpoint
public class Mt900Endpoint {

    public static final String NAMESPACE_URI = "http://www.ftn.com/mt900";

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private RTGSService rtgsService;

    @Autowired
    private ClearingService clearingService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMt900Request")
    @ResponsePayload
    public GetMt900Response mt900(@RequestPayload GetMt900Request request) {
        final GetMt900Response response = new GetMt900Response();
        if(request.getMt900().getWarrantData().getWarrantMessageId().startsWith("Mt102")){
            clearingService.processMT900(request.getMt900());
            response.setMt900("Ok");
            return response;
        }else {
            rtgsService.processMT900(request.getMt900());
            response.setMt900("Ok");
            return response;
        }
    }
}
