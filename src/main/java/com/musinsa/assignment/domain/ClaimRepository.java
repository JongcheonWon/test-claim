package com.musinsa.assignment.domain;

import com.musinsa.assignment.claim.ClaimType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {

    @EntityGraph(attributePaths = {"claimRefund"})
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Claim> findByPurchaseNoAndClaimType(Long purchaseNo, ClaimType claimType);

    List<Claim> findByPurchaseNo(Long purchaseNo);
}
