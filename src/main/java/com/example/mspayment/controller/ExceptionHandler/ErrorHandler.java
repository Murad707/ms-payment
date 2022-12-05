package com.example.mspayment.controller.ExceptionHandler;

import com.example.mspayment.dao.Exceptions.ExceptionResponse;
import com.example.mspayment.dao.Exceptions.NotFound;
import com.example.mspayment.dao.Exceptions.PaymentException;
import com.example.mspayment.model.constants.ExceptionConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.example.mspayment.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static com.example.mspayment.model.constants.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception e){
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
    }

    @ExceptionHandler(NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handle(NotFound ex){
        log.info("Not found"+ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }
@ExceptionHandler(PaymentException.class)
    @ResponseStatus(HttpStatus.PAYMENT_REQUIRED)
    public ExceptionResponse handle(PaymentException e){
        log.info(("Payment is not have"+e));
        return new ExceptionResponse(e.getCode(), e.getMessage());
}

}
