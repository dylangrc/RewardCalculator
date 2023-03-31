package com.dylan.rewardcalculator.exception;

public class DateTimeException extends RuntimeException {

  private final String errorMessage;

  public DateTimeException(String errorMessage) {
    super(errorMessage);
    this.errorMessage = errorMessage;
  }

  public String getErrorMessage() {
    return errorMessage;
  }
}
