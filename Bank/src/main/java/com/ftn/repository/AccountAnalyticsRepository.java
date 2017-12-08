package com.ftn.repository;

import com.ftn.model.database.AccountAnalytics;
import com.ftn.model.database.DailyAccountState;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Created by zlatan on 6/24/17.
 */
public interface AccountAnalyticsRepository extends JpaRepository<AccountAnalytics, Long> {

    Optional<AccountAnalytics> findById(Long id);

    List<AccountAnalytics> findByDailyAccountState(DailyAccountState dailyAccountState);
}
