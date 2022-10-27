package com.musinsa.assignment.domain;

import com.musinsa.assignment.claim.ClaimType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class ClaimRepositoryTest {

    @Autowired
    ClaimRepository claimRepository;

    @Test
    @DisplayName("클레임 저장 및 조회")
    void selectClaim(){


        ClaimProduct claimProduct = new ClaimProduct();
        claimProduct.setProductNo(1L);
        claimProduct.setProductAmount(15000);
        claimProduct.setProductQuantity(1);
        claimProduct.setChangeDatetime(LocalDateTime.now());
        claimProduct.setRegisterDatetime(LocalDateTime.now());

        ClaimRefund claimRefund = new ClaimRefund();
        claimRefund.setClaimDeliveryFee(2500);
        claimRefund.setChangeDatetime(LocalDateTime.now());
        claimRefund.setRegisterDatetime(LocalDateTime.now());

        Claim claim = new Claim();
        claim.setPurchaseNo(1L);
        claim.setClaimType(ClaimType.EXCHANGE);
        claim.setChangeDatetime(LocalDateTime.now());
        claim.setRegisterDatetime(LocalDateTime.now());

        claim.addClaimProduct(claimProduct);
        claim.addClaimRefund(claimRefund);

        Claim save = claimRepository.save(claim);

        List<Claim> select = claimRepository.findByPurchaseNo(1L);

        assertEquals(claim, save);
        assertEquals(claim, select.get(0));
        assertEquals(claimProduct, select.get(0).getClaimProductList().get(0));
        assertEquals(claimRefund, select.get(0).getClaimRefund());

    }
}