package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * Created by zlatan on 6/24/17.
 */

@Entity
@NoArgsConstructor
@Data
public class Mt103Model {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String messageId;

    @Column(length = 8)
    private String swiftDebtorBank;

    @Column(length = 8)
    private String swiftCreditorBank;

    @Column(length = 20)
    private String accountDebtorBank;

    @Column(length = 20)
    private String accountCreditorBank;

    @Column
    private String debtor;

    @Column
    private String creditor;

    @Column
    private String paymentPurpose;

    @Column
    private Date currencyDate;

    @Column
    private Date warrantDate;

    @Column(length = 20)
    private String debtorAccountNumber;

    @Column
    private BigInteger chargeModel;

    @Column
    private String chargeReferencingNumber;

    @Column
    private String creditorAccountNumber;

    @Column
    private BigInteger creditorModel;

    @Column
    private String creditorReferencingNumber;

    @Column
    private Double amount;

    @Column
    private String currency;
}
