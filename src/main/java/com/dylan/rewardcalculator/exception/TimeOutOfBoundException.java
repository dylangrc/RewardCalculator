package com.dylan.rewardcalculator.exception;

public class TimeOutOfBoundException extends DateTimeException {

  public TimeOutOfBoundException(String errorMessage) {
    super(errorMessage);
  }
}
