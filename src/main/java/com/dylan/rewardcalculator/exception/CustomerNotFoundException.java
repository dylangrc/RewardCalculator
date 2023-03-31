package com.dylan.rewardcalculator.exception;

public class CustomerNotFoundException extends CustomerException {

  public CustomerNotFoundException(String errorMessage) {
    super(errorMessage);
  }
}
