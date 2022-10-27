package com.musinsa.assignment.claim;

import com.musinsa.assignment.claim.dto.DeliveryFeeOut;
import com.musinsa.assignment.claim.dto.RegisterClaimIn;
import com.musinsa.assignment.claim.dto.RegisterClaimOut;
import com.musinsa.assignment.common.ClaimBusinessException;
import com.musinsa.assignment.common.ResponseConstants;
import com.musinsa.assignment.domain.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ClaimService {

    private final PurchaseRepository purchaseRepository;
    private final ClaimRepository claimRepository;
    private final Map<ClaimType, DeliveryFee> claimTypeDeliveryFeeMap;

    @Transactional
    public RegisterClaimOut registerClaim(RegisterClaimIn registerClaimIn){

        Purchase purchase = purchaseRepository.findByPurchaseNo(registerClaimIn.getPurchaseNo())
                .orElseThrow( () -> new ClaimBusinessException(ResponseConstants.VALIDATION_ERROR_0100));

        List<Claim> claimList = claimRepository.findByPurchaseNoAndClaimType(registerClaimIn.getPurchaseNo(), ClaimType.RETURN);

        List<OrderProduct> claimableProdList = extractClaimableProdList(purchase.getOrderProductList(), claimList);

        if (claimableProdList.isEmpty()) {
            throw new ClaimBusinessException(ResponseConstants.VALIDATION_ERROR_0101);
        }

        DeliveryPolicy deliveryPolicy = purchase.getDeliveryPolicy();
        DeliveryFee deliveryFee = claimTypeDeliveryFeeMap.get(registerClaimIn.getClaimType());

        long calcDeliveryFee = deliveryFee.calculate(claimableProdList, deliveryPolicy, registerClaimIn.getProductNo());

        log.debug("Return Delivery Fee : {}", calcDeliveryFee);

        LocalDateTime now = LocalDateTime.now();
        ClaimRefund claimRefund = new ClaimRefund();
        claimRefund.setClaimDeliveryFee(calcDeliveryFee);
        claimRefund.setChangeDatetime(now);
        claimRefund.setRegisterDatetime(now);

        Claim claim = new Claim();
        claim.setClaimType(registerClaimIn.getClaimType());
        claim.setPurchaseNo(registerClaimIn.getPurchaseNo());
        claim.setChangeDatetime(now);
        claim.setRegisterDatetime(now);
        claim.addClaimRefund(claimRefund);

        for (long productNo : registerClaimIn.getProductNo()) {
            ClaimProduct claimProduct = new ClaimProduct();
            claimProduct.setProductNo(productNo);
            claimProduct.setProductQuantity(1);
            claimProduct.setChangeDatetime(now);
            claimProduct.setRegisterDatetime(now);
            claim.addClaimProduct(claimProduct);
        }


        claimRepository.save(claim);

        return new RegisterClaimOut(1, calcDeliveryFee);
    }

    private List<OrderProduct> extractClaimableProdList(List<OrderProduct> orderProductList, List<Claim> claimList) {
        List<Long> claimProdNoList = flatMapClaimProdList(claimList);
        return orderProductList
                .stream()
                .filter(orderProduct ->
                        claimProdNoList.stream().noneMatch(claimProdNo -> claimProdNo == orderProduct.getProductNo())
                ).collect(Collectors.toList());
    }

    private List<Long> flatMapClaimProdList(List<Claim> claimList) {
        return claimList
                .stream()
                .flatMap(
                        claim -> claim.getClaimProductList()
                                .stream()
                                .map(ClaimProduct::getProductNo)
                ).collect(Collectors.toList());
    }


    public DeliveryFeeOut getDeliveryFee(long purchaseNo) {

        List<Claim> claimList = claimRepository.findByPurchaseNo(purchaseNo);

        if (claimList.isEmpty()) {
            throw new ClaimBusinessException(ResponseConstants.VALIDATION_ERROR_0101);
        }

        long deliveryFee = claimList.stream()
                .mapToLong(claim -> claim.getClaimRefund().getClaimDeliveryFee())
                .sum();

        return new DeliveryFeeOut(deliveryFee);
    }

}
