package com.org.atm.exceptionhandler;

import com.org.atm.exceptions.IllegalDigitInPinException;
import com.org.atm.exceptions.PinCanNotBeBlankException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

        @ExceptionHandler(value = { PinCanNotBeBlankException.class, IllegalDigitInPinException.class })
        protected ResponseEntity<Object> handleConflict(
                Exception ex, WebRequest request) {
            String bodyOfResponse = ex.getMessage();
            return handleExceptionInternal(ex, bodyOfResponse,
                    new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
        }
    }

