//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:02:25 PM CET 
//


package com.ftn.model.dto.mt102;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.ftn.model.dto.mt102body.Mt102Body;
import com.ftn.model.dto.mt102header.Mt102Header;


/**
 * <p>Java class for mt102 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt102"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://www.ftn.com/mt102header}mt102_header"/&gt;
 *         &lt;element ref="{http://www.ftn.com/mt102body}mt102_body" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mt102", propOrder = {
    "mt102Header",
    "mt102Body"
})
public class Mt102 {

    @XmlElement(name = "mt102_header", namespace = "http://www.ftn.com/mt102header", required = true)
    protected Mt102Header mt102Header;
    @XmlElement(name = "mt102_body", namespace = "http://www.ftn.com/mt102body", required = true)
    protected List<Mt102Body> mt102Body;

    /**
     * Gets the value of the mt102Header property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102Header }
     *     
     */
    public Mt102Header getMt102Header() {
        return mt102Header;
    }

    /**
     * Sets the value of the mt102Header property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102Header }
     *     
     */
    public void setMt102Header(Mt102Header value) {
        this.mt102Header = value;
    }

    /**
     * Gets the value of the mt102Body property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mt102Body property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMt102Body().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Mt102Body }
     * 
     * 
     */
    public List<Mt102Body> getMt102Body() {
        if (mt102Body == null) {
            mt102Body = new ArrayList<Mt102Body>();
        }
        return this.mt102Body;
    }

}