package com.andela.irrigation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordNotFoundException extends RuntimeException {
  public RecordNotFoundException() {
    super("No data found");
  }

  public RecordNotFoundException(String message) {
    super(message);
  }
}
