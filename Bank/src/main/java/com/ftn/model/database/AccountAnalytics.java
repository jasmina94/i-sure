package com.ftn.model.database;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by zlatan on 6/24/17.
 */
@Data
@Entity
@NoArgsConstructor
public class AccountAnalytics {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date warrantDate;

    @Column
    private boolean income; //smer (true ulaz, false izlaz)

    @Column
    private String debtor;

    @Column
    private String creditor;

    @Column
    private String paymentPurpose;

    @Column
    private Date currencyDate;

    @Column(length = 20)
    private String debtorAccount;

    @Column
    private long chargeModel;

    @Column
    private String chargeReferencingNumber;

    @Column(length = 20)
    private String creditorAccount;

    @Column
    private long approvalModel;

    @Column
    private String approvalReferencingNumber;

    @Column
    private BigDecimal amount;

    @Column
    private String currency;

    @ManyToOne
    private DailyAccountState dailyAccountState;

}
