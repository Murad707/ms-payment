package com.example.mspayment.service;

import com.example.mspayment.dao.repository.Repository;
import com.example.mspayment.mapper.PaymentMapper;
import com.example.mspayment.model.PaymentRequest;
import com.example.mspayment.model.PaymentResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.mspayment.mapper.PaymentMapper.mapToEntity;
import static com.example.mspayment.mapper.PaymentMapper.mapToResponse;

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
    public PaymentResponse getPaymentById(Long id){
        log.info("ActionLog.getPaymentById.start:id{}",id);
      var payment=repository.findById(id)
              .orElseThrow(()->{
                      log.error("ActionLog.getPaymentById.error:id{}",id);
                     return new RuntimeException();
              }
                  );

        log.info("ActionLog.getPaymentById.end:id{}",id);
        return mapToResponse(payment);
    }
}
