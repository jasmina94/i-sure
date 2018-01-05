//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:02:25 PM CET 
//


package com.ftn.model.dto.mt102body;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import com.ftn.model.dto.types.TCorporateBody;
import com.ftn.model.dto.types.TCurrencyLabel;
import com.ftn.model.dto.types.TPaymentData;


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
 *         &lt;element name="debtor_data" type="{http://www.ftn.com/types}TCorporateBody"/&gt;
 *         &lt;element name="creditor_data" type="{http://www.ftn.com/types}TCorporateBody"/&gt;
 *         &lt;element name="payment_purpose"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="255"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="amount"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.ftn.com/types&gt;TAmount"&gt;
 *                 &lt;attribute name="currency"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.ftn.com/types}TCurrencyLabel"&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="charge_data" type="{http://www.ftn.com/types}TPaymentData"/&gt;
 *         &lt;element name="income_data" type="{http://www.ftn.com/types}TPaymentData"/&gt;
 *         &lt;element name="warrant_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="warrant_id"&gt;
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
    "debtorData",
    "creditorData",
    "paymentPurpose",
    "amount",
    "chargeData",
    "incomeData",
    "warrantDate"
})
@XmlRootElement(name = "mt102_body")
public class Mt102Body {

    @XmlElement(name = "debtor_data", required = true)
    protected TCorporateBody debtorData;
    @XmlElement(name = "creditor_data", required = true)
    protected TCorporateBody creditorData;
    @XmlElement(name = "payment_purpose", required = true)
    protected String paymentPurpose;
    @XmlElement(required = true)
    protected Mt102Body.Amount amount;
    @XmlElement(name = "charge_data", required = true)
    protected TPaymentData chargeData;
    @XmlElement(name = "income_data", required = true)
    protected TPaymentData incomeData;
    @XmlElement(name = "warrant_date", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar warrantDate;
    @XmlAttribute(name = "warrant_id")
    protected String warrantId;

    /**
     * Gets the value of the debtorData property.
     * 
     * @return
     *     possible object is
     *     {@link TCorporateBody }
     *     
     */
    public TCorporateBody getDebtorData() {
        return debtorData;
    }

    /**
     * Sets the value of the debtorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCorporateBody }
     *     
     */
    public void setDebtorData(TCorporateBody value) {
        this.debtorData = value;
    }

    /**
     * Gets the value of the creditorData property.
     * 
     * @return
     *     possible object is
     *     {@link TCorporateBody }
     *     
     */
    public TCorporateBody getCreditorData() {
        return creditorData;
    }

    /**
     * Sets the value of the creditorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TCorporateBody }
     *     
     */
    public void setCreditorData(TCorporateBody value) {
        this.creditorData = value;
    }

    /**
     * Gets the value of the paymentPurpose property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentPurpose() {
        return paymentPurpose;
    }

    /**
     * Sets the value of the paymentPurpose property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentPurpose(String value) {
        this.paymentPurpose = value;
    }

    /**
     * Gets the value of the amount property.
     * 
     * @return
     *     possible object is
     *     {@link Mt102Body.Amount }
     *     
     */
    public Mt102Body.Amount getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt102Body.Amount }
     *     
     */
    public void setAmount(Mt102Body.Amount value) {
        this.amount = value;
    }

    /**
     * Gets the value of the chargeData property.
     * 
     * @return
     *     possible object is
     *     {@link TPaymentData }
     *     
     */
    public TPaymentData getChargeData() {
        return chargeData;
    }

    /**
     * Sets the value of the chargeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPaymentData }
     *     
     */
    public void setChargeData(TPaymentData value) {
        this.chargeData = value;
    }

    /**
     * Gets the value of the incomeData property.
     * 
     * @return
     *     possible object is
     *     {@link TPaymentData }
     *     
     */
    public TPaymentData getIncomeData() {
        return incomeData;
    }

    /**
     * Sets the value of the incomeData property.
     * 
     * @param value
     *     allowed object is
     *     {@link TPaymentData }
     *     
     */
    public void setIncomeData(TPaymentData value) {
        this.incomeData = value;
    }

    /**
     * Gets the value of the warrantDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWarrantDate() {
        return warrantDate;
    }

    /**
     * Sets the value of the warrantDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWarrantDate(XMLGregorianCalendar value) {
        this.warrantDate = value;
    }

    /**
     * Gets the value of the warrantId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantId() {
        return warrantId;
    }

    /**
     * Sets the value of the warrantId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantId(String value) {
        this.warrantId = value;
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
     *       &lt;attribute name="currency"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.ftn.com/types}TCurrencyLabel"&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
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