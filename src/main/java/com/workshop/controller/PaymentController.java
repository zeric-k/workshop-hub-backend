package com.workshop.controller;

import com.workshop.dto.Payment;
import com.workshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    PaymentRepository paymentRepository;

    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody Map<String, Object> request) {
        Long userId = Long.valueOf(request.get("userId").toString());
        Long workshopId = Long.valueOf(request.get("workshopId").toString());
        Double amount = Double.valueOf(request.get("amount").toString());

        // Insert into DB with status = PENDING
        Payment payment = new Payment(userId, workshopId, amount, "PENDING", UUID.randomUUID().toString());
        paymentRepository.save(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("paymentId", payment.getId());
        response.put("reference", payment.getPaymentReference());
        response.put("status", payment.getStatus());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/confirm/{paymentId}")
    public ResponseEntity<Map<String, Object>> confirmPayment(@PathVariable Long paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "Payment not found"));
        }

        Payment payment = paymentOpt.get();

        // Mock success always (you can randomize success/failure)
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("paymentId", payment.getId());
        response.put("status", payment.getStatus());

        return ResponseEntity.ok(response);
    }

}

