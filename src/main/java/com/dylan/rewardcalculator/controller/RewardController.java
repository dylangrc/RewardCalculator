package com.dylan.rewardcalculator.controller;

import com.dylan.rewardcalculator.entity.Customer;
import com.dylan.rewardcalculator.exception.CustomerNotFoundException;
import com.dylan.rewardcalculator.exception.TimeOutOfBoundException;
import com.dylan.rewardcalculator.service.RewardService;
import com.dylan.rewardcalculator.vo.ErrorVO;
import com.dylan.rewardcalculator.vo.RewardVO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class RewardController {

  private RewardService rewardService;

  @Autowired
  public RewardController(RewardService rewardService) {
    this.rewardService = rewardService;
  }

  @GetMapping("/reward/{customerId}")
  public ResponseEntity<RewardVO> getReward(@PathVariable("customerId") Long id,
      @RequestParam(required = false) String periodEnd) {

    // find if the customer exists
    Customer customer = rewardService.findByCustomerId(id);

    if (customer == null) {
      throw new CustomerNotFoundException("Cannot find the customer");
    }

    // if there is no specific period end time, use current time as the end point
    RewardVO rewardVO;
    if (periodEnd == null) {
      rewardVO = rewardService.getThreeMonthReward(id, LocalDateTime.now());
    } else {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

      DateTimeFormatter toLocalDateTime = new DateTimeFormatterBuilder().append(formatter)
          .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
          .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
          .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
          .toFormatter();

      LocalDateTime localDateTime = LocalDateTime.parse(periodEnd, toLocalDateTime);
      if (localDateTime.isAfter(LocalDateTime.now())) {
        throw new TimeOutOfBoundException("Time out of bound");
      } else {
        rewardVO = rewardService.getThreeMonthReward(id, localDateTime);
      }
    }

    return ResponseEntity.ok(rewardVO);
  }

  @ExceptionHandler(CustomerNotFoundException.class)
  public ResponseEntity<ErrorVO> exceptionHandlerCustomerNotFound(Exception ex) {
    ErrorVO errorVO = new ErrorVO();
    errorVO.setErrorCode(HttpStatus.NOT_FOUND.value());
    errorVO.setMessage(ex.getMessage());
    return new ResponseEntity<>(errorVO, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(TimeOutOfBoundException.class)
  public ResponseEntity<ErrorVO> exceptionHandlerTimeOutOfBoundException(Exception ex) {
    ErrorVO errorVO = new ErrorVO();
    errorVO.setErrorCode(HttpStatus.BAD_REQUEST.value());
    errorVO.setMessage(ex.getMessage());
    return new ResponseEntity<>(errorVO, HttpStatus.BAD_REQUEST);
  }
}
