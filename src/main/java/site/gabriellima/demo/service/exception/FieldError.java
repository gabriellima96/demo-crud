package site.gabriellima.demo.service.exception;

import java.io.Serializable;

public class FieldError implements Serializable {

  private String field;
  private String message;

  public FieldError(String field, String message) {
    this.field = field;
    this.message = message;
  }

  public String getField() {
    return field;
  }

  public String getMessage() {
    return message;
  }
}
