package com.musinsa.assignment.claim;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.EnumMap;
import java.util.Map;

@Configuration
public class ClaimBeanConfig {

    @Bean
    public Map<ClaimType, DeliveryFee> claimTypeDeliveryFeeMap() {
        Map<ClaimType, DeliveryFee> map = new EnumMap<>(ClaimType.class);
        map.put(ClaimType.RETURN, new RefundDeliveryFee());
        map.put(ClaimType.EXCHANGE, new ExchangeDeliveryFee());
        map.put(ClaimType.UNKNOWN, (claimableProdList, deliveryPolicy, claimProductNo) -> {
            throw new IllegalArgumentException("Not Supported Claim Type");
        });
        return map;
    }

}
