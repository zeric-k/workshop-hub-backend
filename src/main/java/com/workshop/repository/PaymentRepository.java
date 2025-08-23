package com.workshop.repository;

import com.workshop.dto.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    boolean existsByUserIdAndWorkshopIdAndStatus(Long userId, Long workshopId, String status);
}
