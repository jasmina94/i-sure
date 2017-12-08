package com.ftn.model.database;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by zlatan on 6/24/17.
 */
@Entity
@NoArgsConstructor
@Data

public class Mt102Model {

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
    private double totalAmount;

    @Column
    private String currency;

    @Column
    private Date currencyDate;

    @Column
    private Date warrantDate;

    @Column
    private boolean sent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mt102Model")
    private List<SingleWarrant> warrants;
}
