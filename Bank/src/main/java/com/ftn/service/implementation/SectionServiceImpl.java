package com.ftn.service.implementation;

import com.ftn.exception.ServiceFaultException;
import com.ftn.model.database.Company;
import com.ftn.model.dto.error.ServiceFault;
import com.ftn.model.dto.section.GetSectionRequest;
import com.ftn.model.dto.section.GetSectionResponse;
import com.ftn.model.dto.section.Section;
import com.ftn.repository.CompanyRepository;
import com.ftn.service.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.Optional;

/**
 * Created by Jasmina on 26/06/2017.
 */
@Service
public class SectionServiceImpl extends WebServiceGatewaySupport implements SectionService {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public void send(Section section) {
        String url = "";
        String accountNumber = section.getSectionHeader().getAccountNumber();
        Optional<Company> company = companyRepository.findByAccountNumber(accountNumber);
        if(company.isPresent()){
            url = company.get().getUrl();
        }else{
            throw new ServiceFaultException("Not found.", new ServiceFault("404", "Not found company with account number in bank for possible account statement!"));
        }

        final Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setClassesToBeBound(GetSectionRequest.class, GetSectionResponse.class);
        setMarshaller(marshaller);
        setUnmarshaller(marshaller);

        final GetSectionRequest getSectionRequest = new GetSectionRequest();
        getSectionRequest.setSection(section);

        try{
            final GetSectionResponse response = (GetSectionResponse) getWebServiceTemplate().marshalSendAndReceive(url, getSectionRequest);
        }catch (RuntimeException e){
            throw new ServiceFaultException("Wrong answer.", new ServiceFault("500", "Sending section to company is not possible!"));
        }
    }
}
