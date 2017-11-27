package com.ftn.endpoint;

import com.ftn.model.dto.statementaccountinquiry.GetStatementAccountInquiryRequest;
import com.ftn.model.dto.statementaccountinquiry.GetStatementAccountInquiryResponse;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.InquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by Jasmina on 24/06/2017.
 */
@Endpoint
public class InquiryEndpoint {

    public static final String NAMESPACE_URI = "http://www.ftn.com/statementAccountInquiry";

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private InquiryService inquiryService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getStatementAccountInquiryRequest")
    @ResponsePayload
    public GetStatementAccountInquiryResponse inquiry(@RequestPayload GetStatementAccountInquiryRequest request) {
        final GetStatementAccountInquiryResponse response = new GetStatementAccountInquiryResponse();
        inquiryService.process(request.getStatementAccountInquiry());
        response.setStatementAccountInquiry("Ok");
        return response;
    }
}
