package com.dylan.rewardcalculator.unitTest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.dylan.rewardcalculator.dao.TransactionRepository;
import com.dylan.rewardcalculator.entity.Customer;
import com.dylan.rewardcalculator.entity.Transaction;
import com.dylan.rewardcalculator.service.RewardServiceImpl;
import com.dylan.rewardcalculator.vo.RewardVO;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RewardServiceTest {

  @Mock
  TransactionRepository transactionRepository;

  @InjectMocks
  RewardServiceImpl rewardService;

  @BeforeEach
  public void setup() {
    List<Transaction> mockList = new ArrayList<>();
    mockList.add(new Transaction(1L, new Customer(), LocalDateTime.now(), 100));
    mockList.add(new Transaction(2L, new Customer(), LocalDateTime.now(), 200));
    mockList.add(new Transaction(3L, new Customer(), LocalDateTime.now(), 90));

    when(transactionRepository.findAllByCustomerIdAndTransactionTimeBetween(any(), any(), any()))
        .thenReturn(mockList);
  }

  @Test
  public void testGetThreeMonthReward() {

    RewardVO rewardVO = rewardService.getThreeMonthReward(1L, LocalDateTime.now());

    assertEquals(rewardVO.getTotalReward(), 340);
  }
}
