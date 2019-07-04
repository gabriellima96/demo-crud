package site.gabriellima.demo.service.exception;

public class ObjectNotFoundException extends RuntimeException {

  public ObjectNotFoundException(String message) {
    super(message);
  }
}
