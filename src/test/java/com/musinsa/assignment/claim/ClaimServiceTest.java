package com.musinsa.assignment.claim;

import com.musinsa.assignment.claim.dto.RegisterClaimIn;
import com.musinsa.assignment.common.ClaimBusinessException;
import com.musinsa.assignment.common.ResponseConstants;
import com.musinsa.assignment.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ClaimServiceTest {

    @Mock
    PurchaseRepository purchaseRepository;

    @Mock
    ClaimRepository claimRepository;

    @Mock
    Map<ClaimType, DeliveryFee> claimTypeDeliveryFeeMap;

    @InjectMocks
    ClaimService claimService;

    @Test
    @DisplayName("잘못된 주문번호 검증 테스트")
    void validatePurchaseNo(){

        RegisterClaimIn registerClaimIn = new RegisterClaimIn(0, Collections.singletonList(0L), ClaimType.UNKNOWN);

        // given
        given(purchaseRepository.findByPurchaseNo(any())).willReturn(Optional.empty());

        // when & then
        ClaimBusinessException claimBusinessException =
                assertThrows(ClaimBusinessException.class, () -> claimService.registerClaim(registerClaimIn));

        // then
        assertEquals(ResponseConstants.VALIDATION_ERROR_0100.getMessage(), claimBusinessException.getMessage());
    }

    @Test
    @DisplayName("클레임 추가 요청 불가 검증 테스트")
    void validateClaimable(){

        Purchase purchase = new Purchase();
        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductNo(1L);
        orderProductList.add(orderProduct);
        purchase.setOrderProductList(orderProductList);

        Claim claim = new Claim();
        ClaimProduct claimProduct = new ClaimProduct();
        claimProduct.setProductNo(1L);
        claim.setClaimProductList(Collections.singletonList(claimProduct));

        RegisterClaimIn registerClaimIn = new RegisterClaimIn(0, Collections.singletonList(1L), ClaimType.UNKNOWN);

        // given
        given(purchaseRepository.findByPurchaseNo(any())).willReturn(Optional.of(purchase));
        given(claimRepository.findByPurchaseNoAndClaimType(any(), any())).willReturn(Collections.singletonList(claim));

        // when & then
        ClaimBusinessException claimBusinessException =
                assertThrows(ClaimBusinessException.class, () -> claimService.registerClaim(registerClaimIn));

        // then
        assertEquals(ResponseConstants.VALIDATION_ERROR_0101.getMessage(), claimBusinessException.getMessage());
    }

    @Test
    @DisplayName("잘못된 상품 번호 클레임 요청 검증 테스트")
    void validateProductNo(){

        Purchase purchase = new Purchase();
        List<OrderProduct> orderProductList = new ArrayList<>();
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setProductNo(1L);
        orderProductList.add(orderProduct);
        purchase.setOrderProductList(orderProductList);

        Claim claim = new Claim();
        ClaimProduct claimProduct = new ClaimProduct();
        claimProduct.setProductNo(2L);
        claim.setClaimProductList(Collections.singletonList(claimProduct));

        RegisterClaimIn registerClaimIn = new RegisterClaimIn(0, Collections.singletonList(2L), ClaimType.RETURN);

        // given
        given(purchaseRepository.findByPurchaseNo(any())).willReturn(Optional.of(purchase));
        given(claimRepository.findByPurchaseNoAndClaimType(any(), any())).willReturn(Collections.singletonList(claim));
        given(claimTypeDeliveryFeeMap.get(ClaimType.RETURN)).willReturn(new RefundDeliveryFee());

        // when & then
        ClaimBusinessException claimBusinessException =
                assertThrows(ClaimBusinessException.class, () -> claimService.registerClaim(registerClaimIn));

        // then
        assertEquals(ResponseConstants.VALIDATION_ERROR_0102.getMessage(), claimBusinessException.getMessage());
    }

}