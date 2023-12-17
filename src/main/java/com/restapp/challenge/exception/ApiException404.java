package com.restapp.challenge.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ApiException404 extends RuntimeException {

  private ErrorCause errorCause;
  public ApiException404(String message, ErrorCause errorCause) {
    super(message);
    this.errorCause = errorCause;
  }
}
