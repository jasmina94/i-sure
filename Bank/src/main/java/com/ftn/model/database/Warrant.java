package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by zlatan on 6/24/17.
 */
@Entity
@NoArgsConstructor
@Data
public class Warrant {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String warrantId;

    @Column
    private String debtor;

    @Column
    private String creditor;

    @Column
    private String paymentPurpose;

    @Column
    private Date warrantDate;

    @Column(length = 20)
    private String debtorAccountNumber;

    @Column
    private int chargeModel;

    @Column
    private String chargeReferencingNumber;

    @Column(length = 20)
    private String creditorAccountNumber;

    @Column
    private int creditorModel;

    @Column
    private String creditorReferencingNumber;

    @Column
    private Double amount;

    @Column
    private String currency;

    @ManyToOne
    private Mt102Model mt102Model;

}
