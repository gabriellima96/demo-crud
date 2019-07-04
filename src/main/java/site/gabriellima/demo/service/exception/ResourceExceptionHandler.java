package site.gabriellima.demo.service.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<ErrorDetails> handleObjectNotFoundException(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorDetails errorDetails =
        new ErrorDetails(
            System.currentTimeMillis(),
            status.value(),
            "Object not found",
            ex.getMessage(),
            request.getContextPath());

    return new ResponseEntity<>(errorDetails, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ValidationErrorDetails validationErrorDetails =
        new ValidationErrorDetails(
            System.currentTimeMillis(),
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Field Validation Error",
            "Check all fields",
            request.getContextPath());

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(
            f ->
                validationErrorDetails.addError(
                    new FieldError(f.getField(), f.getDefaultMessage())));

    return new ResponseEntity<>(validationErrorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
