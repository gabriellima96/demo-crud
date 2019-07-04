package site.gabriellima.demo.service.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorDetails extends ErrorDetails {

  private List<FieldError> errors;

  public ValidationErrorDetails(
      long timestamp, int status, String error, String message, String path) {
    super(timestamp, status, error, message, path);
    this.errors = new ArrayList<>();
  }

  public List<FieldError> getErrors() {
    return errors;
  }

  public void addError(FieldError fieldError) {
    errors.add(fieldError);
  }
}
