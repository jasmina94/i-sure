//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:30:54 PM CET 
//


package com.ftn.model.dto.sectionitem;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ftn.sectionitem package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to generateInquiryInfo new instances of schema derived classes for package: com.ftn.sectionitem
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SectionItem }
     * 
     */
    public SectionItem createSectionItem() {
        return new SectionItem();
    }

    /**
     * Create an instance of {@link SectionItem.PaymentData }
     * 
     */
    public SectionItem.PaymentData createSectionItemPaymentData() {
        return new SectionItem.PaymentData();
    }

}
