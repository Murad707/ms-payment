package com.example.mspayment.service;

import com.example.mspayment.dao.repository.Repository;
import com.example.mspayment.model.PaymentRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.mspayment.mapper.PaymentMapper.mapToEntity;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentsService {
    private final Repository repository;

    public void savePayment(PaymentRequest request){
        log.info("ActionLog.savePayment.start");
        repository.save(mapToEntity(request));
        log.info("ActionLog.savePayment.end");

    }
}
