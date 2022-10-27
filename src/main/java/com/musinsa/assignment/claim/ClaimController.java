package com.musinsa.assignment.claim;

import com.musinsa.assignment.claim.dto.DeliveryFeeOut;
import com.musinsa.assignment.claim.dto.RegisterClaimIn;
import com.musinsa.assignment.claim.dto.RegisterClaimOut;
import com.musinsa.assignment.common.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/claim")
@RestController
public class ClaimController {

    private final ClaimService claimService;

    @PostMapping
    public ResponseEntity<CommonResponse<RegisterClaimOut>> registerClaim(@RequestBody RegisterClaimIn registerClaimIn){

        log.info("Register Claim Input : {}", registerClaimIn);
        RegisterClaimOut registerClaimOut = claimService.registerClaim(registerClaimIn);
        log.info("Register Claim Output : {}", registerClaimOut);

        return ResponseEntity.ok(CommonResponse.createNormalResponse(registerClaimOut));
    }

    @GetMapping("/{purchaseNo}")
    public ResponseEntity<CommonResponse<DeliveryFeeOut>> getClaimReturnDeliveryFee(@PathVariable("purchaseNo") long purchaseNo) {

        log.info("GET Claim Return Delivery Fee Input - purchaseNo:{}", purchaseNo);
        DeliveryFeeOut deliveryFeeOut = claimService.getDeliveryFee(purchaseNo);
        log.info("GET Claim Return Delivery Fee Output : {}", deliveryFeeOut);

        return ResponseEntity.ok(CommonResponse.createNormalResponse(deliveryFeeOut));
    }
}
