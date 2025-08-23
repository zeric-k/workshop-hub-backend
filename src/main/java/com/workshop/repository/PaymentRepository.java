package com.workshop.repository;

import com.workshop.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByUserIdAndWorkshopIdAndStatus(Long userId, Long workshopId, String status);
    List<Payment> findByUserId(Long userId);
}
