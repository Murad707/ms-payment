package com.example.mspayment.mapper;

import com.example.mspayment.dao.entity.PaymentEntity;
import com.example.mspayment.model.PaymentRequest;
import com.example.mspayment.model.PaymentResponse;
import com.example.mspayment.model.PaymentUpdate;

public class PaymentMapper {
public static PaymentEntity mapToEntity(PaymentRequest request){
        return PaymentEntity.builder()
                .amount(request.getAmount())
                .descriptions(request.getDescriptions())
                .build();
    }
    public static PaymentResponse mapToResponse(PaymentEntity entity){
        return PaymentResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .descriptions(entity.getDescriptions())
                .build();
    }
    public static void update(PaymentEntity entity, PaymentUpdate update){
        entity.setAmount(update.getAmount());
        entity.setDescriptions(update.getDescriptions());
    }
}
