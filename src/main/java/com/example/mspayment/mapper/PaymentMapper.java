package com.example.mspayment.mapper;

import com.example.mspayment.dao.entity.PaymentEntity;
import com.example.mspayment.model.PaymentRequest;

public class PaymentMapper {

    public static PaymentEntity mapToEntity(PaymentRequest request){
        return PaymentEntity.builder()
                .amount(request.getAmount())
                .descriptions(request.getDescription())
                .build();
    }
}
