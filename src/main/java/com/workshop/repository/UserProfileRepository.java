package com.workshop.repository;

import com.workshop.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    // Custom finder if you need to fetch by userId
    UserProfile findByUserId(Long userId);
}
