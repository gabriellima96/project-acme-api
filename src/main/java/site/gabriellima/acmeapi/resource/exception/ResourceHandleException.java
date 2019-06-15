package site.gabriellima.acmeapi.resource.exception;

import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import site.gabriellima.acmeapi.exception.ErrorDetails;
import site.gabriellima.acmeapi.exception.FieldError;
import site.gabriellima.acmeapi.exception.ObjectNotFoundException;
import site.gabriellima.acmeapi.exception.ValidationErrorDetails;

@ControllerAdvice
public class ResourceHandleException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ObjectNotFoundException.class)
  public ResponseEntity<?> handleObjectNotFoundException(
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
        .forEach(f -> validationErrorDetails.addError(new FieldError(f.getField(), f.getDefaultMessage())));

    return new ResponseEntity<>(validationErrorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {

    ErrorDetails errorDetails =
        new ErrorDetails(
            System.currentTimeMillis(),
            status.value(),
            "Malformed JSON",
            ex.getMessage(),
            request.getContextPath());

    return new ResponseEntity<>(errorDetails, headers, status);
  }

  @Override
  protected ResponseEntity<Object> handleExceptionInternal(
      Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

    ErrorDetails errorDetails =
        new ErrorDetails(
            System.currentTimeMillis(),
            status.value(),
            "Internal Exception",
            ex.getMessage(),
            request.getContextPath());

    return new ResponseEntity<>(errorDetails, headers, status);
  }
}
