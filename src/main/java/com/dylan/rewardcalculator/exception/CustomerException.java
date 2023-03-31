package com.dylan.rewardcalculator.exception;

public class CustomerException extends RuntimeException {

  private final String errorMessage;

  public CustomerException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
