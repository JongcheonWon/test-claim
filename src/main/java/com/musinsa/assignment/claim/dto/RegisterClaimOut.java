package com.musinsa.assignment.claim.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;


@ToString
@Getter
@RequiredArgsConstructor
public class RegisterClaimOut {
    private final int processCount;
    private final long registerRefundDeliveryFee;
}
