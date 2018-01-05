//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:02:25 PM CET 
//


package com.ftn.model.dto.types;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for TWarrantData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TWarrantData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="currency_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="amount"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.ftn.com/types&gt;TAmount"&gt;
 *                 &lt;attribute name="currency" type="{http://www.ftn.com/types}TCurrencyLabel" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TWarrantData", propOrder = {
    "currencyDate",
    "amount"
})
public class TWarrantData {

    @XmlElement(name = "currency_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currencyDate;
    @XmlElement(required = true)
    protected TWarrantData.Amount amount;

    /**
     * Gets the value of the currencyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCurrencyDate() {
        return currencyDate;
    }

    /**
     * Sets the value of the currencyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCurrencyDate(XMLGregorianCalendar value) {
        this.currencyDate = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link TWarrantData.Amount }
     *     
     */
    public TWarrantData.Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TWarrantData.Amount }
     *     
     */
    public void setAmount(TWarrantData.Amount value) {
        this.amount = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.ftn.com/types&gt;TAmount"&gt;
     *       &lt;attribute name="currency" type="{http://www.ftn.com/types}TCurrencyLabel" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class Amount {

        @XmlValue
        protected BigDecimal value;
        @XmlAttribute(name = "currency")
        protected TCurrencyLabel currency;

        /**
         * Gets the value of the value property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getValue() {
            return value;
        }

        /**
         * Sets the value of the value property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setValue(BigDecimal value) {
            this.value = value;
        }

        /**
         * Gets the value of the currency property.
         * 
         * @return
         *     possible object is
         *     {@link TCurrencyLabel }
         *     
         */
        public TCurrencyLabel getCurrency() {
            return currency;
        }

        /**
         * Sets the value of the currency property.
         * 
         * @param value
         *     allowed object is
         *     {@link TCurrencyLabel }
         *     
         */
        public void setCurrency(TCurrencyLabel value) {
            this.currency = value;
        }

    }

}