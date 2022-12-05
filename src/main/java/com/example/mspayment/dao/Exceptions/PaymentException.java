package com.example.mspayment.dao.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PaymentException extends RuntimeException{
    public PaymentException(String message, String code) {
        super(message);
        this.code = code;
    }

    private String code;
}
