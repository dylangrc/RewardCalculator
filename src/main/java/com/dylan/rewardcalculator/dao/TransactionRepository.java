package com.dylan.rewardcalculator.dao;

import com.dylan.rewardcalculator.entity.Transaction;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  List<Transaction> findAllByCustomerIdAndTransactionTimeBetween(Long id, LocalDateTime start,
      LocalDateTime end);
}
