package com.andela.irrigation.exception;

import lombok.Getter;

@Getter
public class SensorNotReachableException extends Exception {
  private int code;
  private String message;

  public SensorNotReachableException(String message, int code) {
    super(message);
    this.code = code;
  }
}
