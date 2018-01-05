//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.11.26 at 09:08:03 PM CET 
//


package com.ftn.model.dto.mt103;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;
import com.ftn.model.dto.types.TBankData;
import com.ftn.model.dto.types.TCorporateBody;
import com.ftn.model.dto.types.TPaymentData;


/**
 * <p>Java class for mt103 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mt103"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="debtor_data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{http://www.ftn.com/types}TCorporateBody"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="bank_data" type="{http://www.ftn.com/types}TBankData"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="creditor_data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{http://www.ftn.com/types}TCorporateBody"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="podaci_o_banci" type="{http://www.ftn.com/types}TBankData"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="payment_data"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="payment_purpose"&gt;
 *                     &lt;simpleType&gt;
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                         &lt;maxLength value="255"/&gt;
 *                       &lt;/restriction&gt;
 *                     &lt;/simpleType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="amount"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.ftn.com/types&gt;TAmount"&gt;
 *                           &lt;attribute name="currency"&gt;
 *                             &lt;simpleType&gt;
 *                               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                                 &lt;enumeration value="RSD"/&gt;
 *                                 &lt;enumeration value="USD"/&gt;
 *                                 &lt;enumeration value="EUR"/&gt;
 *                               &lt;/restriction&gt;
 *                             &lt;/simpleType&gt;
 *                           &lt;/attribute&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="charge_data" type="{http://www.ftn.com/types}TPaymentData"/&gt;
 *                   &lt;element name="income_data" type="{http://www.ftn.com/types}TPaymentData"/&gt;
 *                   &lt;element name="warrant_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                   &lt;element name="currency_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
@XmlType(name = "mt103", propOrder = {
    "debtorData",
    "creditorData",
    "paymentData"
})
public class Mt103 {

    @XmlElement(name = "debtor_data", required = true)
    protected Mt103 .DebtorData debtorData;
    @XmlElement(name = "creditor_data", required = true)
    protected Mt103 .CreditorData creditorData;
    @XmlElement(name = "payment_data", required = true)
    protected Mt103 .PaymentData paymentData;
    @XmlAttribute(name = "message_id")
    protected String messageId;

    /**
     * Gets the value of the debtorData property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 .DebtorData }
     *     
     */
    public Mt103 .DebtorData getDebtorData() {
        return debtorData;
    }

    /**
     * Sets the value of the debtorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 .DebtorData }
     *     
     */
    public void setDebtorData(Mt103 .DebtorData value) {
        this.debtorData = value;
    }

    /**
     * Gets the value of the creditorData property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 .CreditorData }
     *     
     */
    public Mt103 .CreditorData getCreditorData() {
        return creditorData;
    }

    /**
     * Sets the value of the creditorData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 .CreditorData }
     *     
     */
    public void setCreditorData(Mt103 .CreditorData value) {
        this.creditorData = value;
    }

    /**
     * Gets the value of the paymentData property.
     * 
     * @return
     *     possible object is
     *     {@link Mt103 .PaymentData }
     *     
     */
    public Mt103 .PaymentData getPaymentData() {
        return paymentData;
    }

    /**
     * Sets the value of the paymentData property.
     * 
     * @param value
     *     allowed object is
     *     {@link Mt103 .PaymentData }
     *     
     */
    public void setPaymentData(Mt103 .PaymentData value) {
        this.paymentData = value;
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{http://www.ftn.com/types}TCorporateBody"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="podaci_o_banci" type="{http://www.ftn.com/types}TBankData"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "bankData"
    })
    public static class CreditorData
        extends TCorporateBody
    {

        @XmlElement(name = "bank_data", required = true)
        protected TBankData bankData;

        /**
         * Gets the value of the bankData property.
         * 
         * @return
         *     possible object is
         *     {@link TBankData }
         *     
         */
        public TBankData getBankData() {
            return bankData;
        }

        /**
         * Sets the value of the bankData property.
         * 
         * @param value
         *     allowed object is
         *     {@link TBankData }
         *     
         */
        public void setBankData(TBankData value) {
            this.bankData = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{http://www.ftn.com/types}TCorporateBody"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="bank_data" type="{http://www.ftn.com/types}TBankData"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "bankData"
    })
    public static class DebtorData
        extends TCorporateBody
    {

        @XmlElement(name = "bank_data", required = true)
        protected TBankData bankData;

        /**
         * Gets the value of the bankData property.
         * 
         * @return
         *     possible object is
         *     {@link TBankData }
         *     
         */
        public TBankData getBankData() {
            return bankData;
        }

        /**
         * Sets the value of the bankData property.
         * 
         * @param value
         *     allowed object is
         *     {@link TBankData }
         *     
         */
        public void setBankData(TBankData value) {
            this.bankData = value;
        }

    }


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
     *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *                       &lt;enumeration value="RSD"/&gt;
     *                       &lt;enumeration value="USD"/&gt;
     *                       &lt;enumeration value="EUR"/&gt;
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
     *         &lt;element name="currency_date" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "paymentPurpose",
        "amount",
        "chargeData",
        "incomeData",
        "warrantDate",
        "currencyDate"
    })
    public static class PaymentData {

        @XmlElement(name = "payment_purpose", required = true)
        protected String paymentPurpose;
        @XmlElement(required = true)
        protected Mt103 .PaymentData.Amount amount;
        @XmlElement(name = "charge_data", required = true)
        protected TPaymentData chargeData;
        @XmlElement(name = "income_data", required = true)
        protected TPaymentData incomeData;
        @XmlElement(name = "warrant_date", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar warrantDate;
        @XmlElement(name = "currency_date", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar currencyDate;

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
         *     {@link Mt103 .PaymentData.Amount }
         *     
         */
        public Mt103 .PaymentData.Amount getAmount() {
            return amount;
        }

        /**
         * Sets the value of the amount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Mt103 .PaymentData.Amount }
         *     
         */
        public void setAmount(Mt103 .PaymentData.Amount value) {
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
         *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
         *             &lt;enumeration value="RSD"/&gt;
         *             &lt;enumeration value="USD"/&gt;
         *             &lt;enumeration value="EUR"/&gt;
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
            protected String currency;

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
             *     {@link String }
             *     
             */
            public String getCurrency() {
                return currency;
            }

            /**
             * Sets the value of the currency property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCurrency(String value) {
                this.currency = value;
            }

        }

    }

}