package com.example.mspayment.dao.Exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotFound extends RuntimeException{
    private String code;

    public NotFound(String message, String code) {
        super(message);
        this.code = code;
    }
}
