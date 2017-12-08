package com.ftn.repository;

import com.ftn.model.database.Account;
import com.ftn.model.database.DailyAccountState;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface DailyAccountStateRepository extends JpaRepository<DailyAccountState, Long> {

    Optional<DailyAccountState> findById(Long id);

    Optional<DailyAccountState> findByAccountAndDate(Account account, Date date);
}
