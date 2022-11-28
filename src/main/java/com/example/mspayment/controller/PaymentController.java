package com.example.mspayment.controller;

import com.example.mspayment.model.PaymentRequest;
import com.example.mspayment.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private  final PaymentsService paymentService;
    @PostMapping
    public void savePayment(@RequestBody PaymentRequest request){
        paymentService.savePayment(request);
    }
}
