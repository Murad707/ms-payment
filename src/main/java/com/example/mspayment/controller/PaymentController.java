package com.example.mspayment.controller;

import com.example.mspayment.model.PaymentRequest;
import com.example.mspayment.model.PaymentResponse;
import com.example.mspayment.model.PaymentUpdate;
import com.example.mspayment.service.PaymentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private  final PaymentsService paymentService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePayment(@RequestBody PaymentRequest request){
        paymentService.savePayment(request);
    }
    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id){
    return paymentService.getPaymentById(id);
    }

    @GetMapping
    public List<PaymentResponse> getPayments(){
        return paymentService.getPayments1();
    }
    @PutMapping("/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody PaymentUpdate update){
        paymentService.update(id, update);
    }
}
