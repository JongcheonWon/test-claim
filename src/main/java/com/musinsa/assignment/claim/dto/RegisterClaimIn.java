package com.musinsa.assignment.claim.dto;

import com.musinsa.assignment.claim.ClaimType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.List;


@RequiredArgsConstructor
@Getter
@ToString
public class RegisterClaimIn {
    private final long purchaseNo;
    private final List<Long> productNoList;
    private final ClaimType claimType;
}
