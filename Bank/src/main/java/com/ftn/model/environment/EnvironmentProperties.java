package com.ftn.model.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by Jasmina on 24/06/2017.
 */
@Component
@ConfigurationProperties(prefix="environment")
public class EnvironmentProperties {

    private String swiftCode;

    private String nbsUrl;

    private String concentratorUrl;

    private String pccUrl;


    public void setSwiftCode(String swiftCode){
        this.swiftCode = swiftCode;
    }

    public String getSwiftCode(){
        return this.swiftCode;
    }

    public String getNbsUrl() {
        return nbsUrl;
    }

    public void setNbsUrl(String nbsUrl) {
        this.nbsUrl = nbsUrl;
    }

    public String getConcentratorUrl() {
        return concentratorUrl;
    }

    public void setConcentratorUrl(String concentratorUrl) {
        this.concentratorUrl = concentratorUrl;
    }

    public String getPccUrl() {
        return pccUrl;
    }

    public void setPccUrl(String pccUrl) {
        this.pccUrl = pccUrl;
    }
}
