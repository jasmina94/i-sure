package com.ftn.service;


import com.ftn.model.database.Mt102Model;
import com.ftn.model.dto.mt102.Mt102;
import com.ftn.model.dto.mt900.Mt900;
import com.ftn.model.dto.mt910.Mt910;

/**
 * Created by zlatan on 6/25/17.
 */
public interface ClearingService {

    Mt102 createMT102(Mt102Model mt102Model);

    void sendMT102(Mt102 mt102);

    String processMT900(Mt900 mt900);

    String processMT910(Mt910 mt910);

    void save(Mt102 mt102);
}
