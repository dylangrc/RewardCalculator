package com.dylan.rewardcalculator.vo;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RewardVO {

  private List<MonthRewardVO> monthRewardVO;
  private Integer totalReward;
}
