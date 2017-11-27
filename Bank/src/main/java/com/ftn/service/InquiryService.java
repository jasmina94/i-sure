package com.ftn.service;


import com.ftn.model.dto.statementaccountinquiry.StatementAccountInquiry;

/**
 * Created by zlatan on 26/06/2017.
 */
public interface InquiryService {

    void process(StatementAccountInquiry statementAccountInquiry);
}
