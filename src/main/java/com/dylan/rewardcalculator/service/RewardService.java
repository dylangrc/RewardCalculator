package com.dylan.rewardcalculator.service;

import com.dylan.rewardcalculator.entity.Customer;
import com.dylan.rewardcalculator.vo.RewardVO;
import java.time.LocalDateTime;

public interface RewardService {

  Customer findByCustomerId(Long id);

  RewardVO getThreeMonthReward(Long id, LocalDateTime periodEnd);
}
