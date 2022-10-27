package com.musinsa.assignment.claim;

import com.musinsa.assignment.common.ClaimBusinessException;
import com.musinsa.assignment.common.ResponseConstants;
import com.musinsa.assignment.domain.DeliveryPolicy;
import com.musinsa.assignment.domain.OrderProduct;

import java.util.List;
import java.util.stream.Collectors;

public class RefundDeliveryFee implements DeliveryFee {

    @Override
    public long calculate(List<OrderProduct> claimableProdList, DeliveryPolicy deliveryPolicy, List<Long> claimProductNoList) {


        List<OrderProduct> matchedClaimProductList = claimableProdList.stream()
                .filter(orderProduct -> claimProductNoList.contains(orderProduct.getProductNo()))
                .collect(Collectors.toList());


        if(matchedClaimProductList.size() != claimProductNoList.size()){
            throw new ClaimBusinessException(ResponseConstants.VALIDATION_ERROR_0102);
        }

        if ((claimableProdList.size() - claimProductNoList.size()) > 0) {
            return deliveryPolicy.getOneWayDeliveryFee();
        }

        return (deliveryPolicy.getOneWayDeliveryFee() * 2);
    }
}
