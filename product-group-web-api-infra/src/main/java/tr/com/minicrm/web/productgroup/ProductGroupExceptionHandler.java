package tr.com.minicrm.web.productgroup;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tips.smallapps.productgroup.business.ProductGroupAlreadyExistException;
import tips.smallapps.productgroup.business.ProductGroupDoesNotExistException;
import tips.smallapps.web.generated.productgroup.model.OperationResponse;

@ControllerAdvice
public class ProductGroupExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = {ProductGroupAlreadyExistException.class})
  protected ResponseEntity<OperationResponse> handleBusinessException(ProductGroupAlreadyExistException ex, WebRequest request) {
    OperationResponse response = new OperationResponse().code("PG001").message(ex.getMessage());
    return new ResponseEntity<OperationResponse>(response, HttpStatus.BAD_REQUEST);

  }

  @ExceptionHandler(value = {ProductGroupDoesNotExistException.class})
  protected ResponseEntity<OperationResponse> handleBusinessException(ProductGroupDoesNotExistException ex, WebRequest request) {
    OperationResponse response = new OperationResponse().code("PG002").message(ex.getMessage());
    return new ResponseEntity<OperationResponse>(response, HttpStatus.NOT_FOUND);

  }

}

