package com.flashstack.loans.repository;

import com.flashstack.loans.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoansRepository extends JpaRepository<Loans, Long> {
    Optional<Loans> findByMobileNumber(String mobileNumber);
    boolean existsByMobileNumber(String mobileNumber);
    void deleteByMobileNumber(String mobileNumber);
}
