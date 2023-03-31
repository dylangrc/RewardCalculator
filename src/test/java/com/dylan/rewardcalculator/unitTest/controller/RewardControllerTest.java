package com.dylan.rewardcalculator.unitTest.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.dylan.rewardcalculator.entity.Customer;
import com.dylan.rewardcalculator.service.RewardService;
import com.dylan.rewardcalculator.vo.RewardVO;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class RewardControllerTest {

  @MockBean
  RewardService rewardService;

  @Autowired
  private MockMvc mockMvc;


  @Test
  public void testCustomerNotFound() throws Exception {
    Mockito.when(rewardService.findByCustomerId(anyLong())).thenReturn(null);
    mockMvc.perform(get("/customer/reward/4"))
        .andExpect(status().isNotFound())
        .andExpect(jsonPath("$.errorCode").value(404))
        .andExpect(jsonPath("$.message").value("Cannot find the customer"));
  }

  @Test
  public void testGetRewardWithNoTimeInput() throws Exception {
    Mockito.when(rewardService.findByCustomerId(anyLong()))
        .thenReturn(new Customer(1L, "testUser"));
    Mockito.when(rewardService.getThreeMonthReward(any(), any()))
        .thenReturn(new RewardVO(new ArrayList<>(), 100));

    mockMvc.perform(get("/customer/reward/2"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalReward").value(100));
  }

  @Test
  public void testGetRewardWithTimeInput() throws Exception {
    Mockito.when(rewardService.findByCustomerId(anyLong()))
        .thenReturn(new Customer(1L, "testUser"));
    Mockito.when(rewardService.getThreeMonthReward(any(), any()))
        .thenReturn(new RewardVO(new ArrayList<>(), 100));

    mockMvc.perform(get("/customer/reward/2?periodEnd=2022-12-30"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.totalReward").value(100));
  }
}
