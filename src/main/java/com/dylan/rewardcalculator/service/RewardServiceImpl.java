package com.dylan.rewardcalculator.service;

import com.dylan.rewardcalculator.dao.CustomerRepository;
import com.dylan.rewardcalculator.dao.TransactionRepository;
import com.dylan.rewardcalculator.entity.Customer;
import com.dylan.rewardcalculator.entity.Transaction;
import com.dylan.rewardcalculator.vo.MonthRewardVO;
import com.dylan.rewardcalculator.vo.RewardVO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RewardServiceImpl implements RewardService {

  private CustomerRepository customerRepository;
  private TransactionRepository transactionRepository;

  @Autowired
  public RewardServiceImpl(TransactionRepository transactionRepository,
      CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
    this.transactionRepository = transactionRepository;
  }

  @Override
  public RewardVO getThreeMonthReward(Long id, LocalDateTime periodEnd) {
    List<Transaction> transactions = transactionRepository.findAllByCustomerIdAndTransactionTimeBetween(
        id, periodEnd.minusMonths(3), periodEnd);

    Map<String, Integer> monthRewardMap = new HashMap<>();
    int totalReward = 0;

    for (Transaction t : transactions) {

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
      String transactionMonthYear = t.getTransactionTime().format(formatter);
      Integer reward = calculateReward(t.getAmount());

      totalReward += reward;

      monthRewardMap.compute(transactionMonthYear, (k, v) -> v == null ? reward : v + reward);
    }

    List<MonthRewardVO> monthRewardVOList = monthRewardMap.entrySet().stream()
        .map(entry -> new MonthRewardVO(entry.getKey(), entry.getValue()))
        .collect(Collectors.toList());

    return new RewardVO(monthRewardVOList, totalReward);
  }

  @Override
  public Customer findByCustomerId(Long id) {

    return customerRepository.findById(id).orElse(null);

  }

  public Integer calculateReward(Integer amount) {
    int result = 0;
    if (amount > 50) {
      result += Math.min(50, amount - 50);

      if (amount > 100) {
        result += (amount - 100) * 2;
      }
    }
    return result;
  }
}
