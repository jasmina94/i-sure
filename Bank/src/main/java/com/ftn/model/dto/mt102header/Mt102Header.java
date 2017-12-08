//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:02:25 PM CET 
//


package com.ftn.model.dto.mt102header;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import com.ftn.model.dto.types.TBankData;
import com.ftn.model.dto.types.TCurrencyLabel;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="debtor_bank_data" type="{http://www.ftn.com/types}TBankData"/&gt;
 *         &lt;element name="creditor_bank_data" type="{http://www.ftn.com/types}TBankData"/&gt;
 *         &lt;element name="total_amount"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal"&gt;
 *               &lt;totalDigits value="15"/&gt;
 *               &lt;fractionDigits value="2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="currency_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="currency_label" type="{http://www.ftn.com/types}TCurrencyLabel"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="message_id"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;maxLength value="50"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "debtorBankData",
    "creditorBankData",
    "totalAmount",
    "currencyDate",
    "date",
    "currencyLabel"
})
@XmlRootElement(name = "mt102_header")
public class Mt102Header {

    @XmlElement(name = "debtor_bank_data", required = true)
    protected TBankData debtorBankData;
    @XmlElement(name = "creditor_bank_data", required = true)
    protected TBankData creditorBankData;
    @XmlElement(name = "total_amount", required = true)
    protected BigDecimal totalAmount;
    @XmlElement(name = "currency_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar currencyDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    @XmlElement(name = "currency_label", required = true)
    @XmlSchemaType(name = "string")
    protected TCurrencyLabel currencyLabel;
    @XmlAttribute(name = "message_id")
    protected String messageId;

    /**
     * Gets the value of the debtorBankData property.
     * 
     * @return
     *     possible object is
     *     {@link TBankData }
     *     
     */
    public TBankData getDebtorBankData() {
        return debtorBankData;
    }

    /**
     * Sets the value of the debtorBankData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBankData }
     *     
     */
    public void setDebtorBankData(TBankData value) {
        this.debtorBankData = value;
    }

    /**
     * Gets the value of the creditorBankData property.
     * 
     * @return
     *     possible object is
     *     {@link TBankData }
     *     
     */
    public TBankData getCreditorBankData() {
        return creditorBankData;
    }

    /**
     * Sets the value of the creditorBankData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TBankData }
     *     
     */
    public void setCreditorBankData(TBankData value) {
        this.creditorBankData = value;
    }

    /**
     * Gets the value of the totalAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    /**
     * Sets the value of the totalAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmount(BigDecimal value) {
        this.totalAmount = value;
    }

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
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the currencyLabel property.
     * 
     * @return
     *     possible object is
     *     {@link TCurrencyLabel }
     *     
     */
    public TCurrencyLabel getCurrencyLabel() {
        return currencyLabel;
    }

    /**
     * Sets the value of the currencyLabel property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCurrencyLabel }
     *     
     */
    public void setCurrencyLabel(TCurrencyLabel value) {
        this.currencyLabel = value;
    }

    /**
     * Gets the value of the messageId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageId() {
        return messageId;
    }

    /**
     * Sets the value of the messageId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageId(String value) {
        this.messageId = value;
    }

}
