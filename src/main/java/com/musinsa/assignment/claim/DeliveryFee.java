package com.musinsa.assignment.claim;

import com.musinsa.assignment.domain.DeliveryPolicy;
import com.musinsa.assignment.domain.OrderProduct;

import java.util.List;

public interface DeliveryFee {

    long calculate(List<OrderProduct> claimableProdList, DeliveryPolicy deliveryPolicy, List<Long> claimProductNoList);

}
