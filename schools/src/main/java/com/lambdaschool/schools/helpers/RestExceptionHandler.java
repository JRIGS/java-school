package com.lambdaschool.schools.helpers;

import com.lambdaschool.schools.exceptions.ResourceNotFoundException;
import com.lambdaschool.schools.models.ErrorDetails;
import com.lambdaschool.schools.services.HelperFunctions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private HelperFunctions helperFunctions;

    public RestExceptionHandler(){
        super();
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException rnfe) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(new Date());
        errorDetails.setStatus(HttpStatus.NOT_FOUND.value());
        errorDetails.setTitle("Resource Not Found");
        errorDetails.setDetails(rnfe.getMessage());
        errorDetails.setDevelopermessage(rnfe.getClass().getName());
        errorDetails.setErrors(helperFunctions.getConstraintViolations(rnfe));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorDetails errorDetail = new ErrorDetails();

        errorDetail.setTitle("Rest Internal Exception");
        errorDetail.setStatus(status.value());
        errorDetail.setDetails(ex.getMessage());
        errorDetail.setTimestamp(new Date());
        errorDetail.setDevelopermessage(ex.getClass().getName());
        errorDetail.setErrors(helperFunctions.getConstraintViolations(ex));

        return new ResponseEntity<>(errorDetail, headers, status);
    }
}
