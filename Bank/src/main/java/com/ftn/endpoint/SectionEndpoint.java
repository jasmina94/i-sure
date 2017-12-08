package com.ftn.endpoint;

import com.ftn.model.dto.section.GetSectionRequest;
import com.ftn.model.dto.section.GetSectionResponse;
import com.ftn.model.environment.EnvironmentProperties;
import com.ftn.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by Jasmina on 24/06/2017.
 */
@Endpoint
public class SectionEndpoint {

    public static final String NAMESPACE_URI = "http://www.ftn.com/section";

    @Autowired
    private EnvironmentProperties environmentProperties;

    @Autowired
    private SectionService sectionService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getSectionRequest")
    @ResponsePayload
    public GetSectionResponse section(@RequestPayload GetSectionRequest request) {
        final GetSectionResponse response = new GetSectionResponse();
        sectionService.send(request.getSection());
        response.setSection("Ok");
        return response;
    }
}
