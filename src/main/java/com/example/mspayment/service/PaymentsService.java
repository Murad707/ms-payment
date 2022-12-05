package com.example.mspayment.service;


import com.example.mspayment.client.CountryClient;
import com.example.mspayment.dao.Exceptions.NotFound;
import com.example.mspayment.dao.Exceptions.PaymentException;
import com.example.mspayment.dao.entity.PaymentEntity;
import com.example.mspayment.dao.repository.Repository;
import com.example.mspayment.mapper.PaymentMapper;
import com.example.mspayment.model.PaymentRequest;
import com.example.mspayment.model.PaymentResponse;
import com.example.mspayment.model.PaymentUpdate;
import com.example.mspayment.model.constants.ExceptionConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.mspayment.mapper.PaymentMapper.*;

import static com.example.mspayment.mapper.PaymentMapper.mapToResponse;
import static com.example.mspayment.model.constants.ExceptionConstants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentsService {
    private final Repository repository;
    private final CountryClient countryClient;


    public void savePayment(PaymentRequest request){
        log.info("ActionLog.savePayment.start");
        countryClient.getAvailableCountry(request.getCurrency())
        .stream()
        .filter(countryClient->countryClient.getRemainingLimit().compareTo(request.getAmount())>0)
        .findFirst()
           .orElseThrow(
              ()->new NotFound(String.format(COUNTRY_MESSAGE_NOT_FOUND,request.getAmount(),request.getCurrency())
                      ,COUNTRY_CODE_NOT_FOUND));

        repository.save(mapToEntity(request));
        log.info("ActionLog.savePayment.end");
        throw new RuntimeException();
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
    public List<PaymentResponse> getPayments1(){
        log.info("ActionLog.getPayments.start:");
      var payments=repository
              .findAll()
                .stream()
                .map(payment-> mapToResponse(payment))
              .collect(Collectors.toList());
        log.info("ActionLog.getPayments.end:");
      return payments;
    }
    public void update(Long id,PaymentUpdate update){
        log.info("ActionLog.update.start:id{}",id);
       var payment=fetchPayment(id);

        PaymentMapper.update(payment,update);
       repository.save(payment);
        log.info("ActionLog.update.end:id{}",id);
    }

    private PaymentEntity fetchPayment(Long id){
        return repository.findById(id).orElseThrow(RuntimeException::new);
    }


}
