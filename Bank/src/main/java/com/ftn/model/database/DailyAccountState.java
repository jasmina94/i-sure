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
@Data
@NoArgsConstructor
public class DailyAccountState {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private Date date;

    @Column
    private double previousState;

    @Column
    private int numberOfChargeChanges;

    @Column
    private int numberOfIncomeChanges;

    @Column
    private double totalCharge;

    @Column
    private double totalIncome;

    @Column
    private double newState;

    @ManyToOne
    private Account account;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "dailyAccountState")
    private List<AccountAnalytics> analytics;
}