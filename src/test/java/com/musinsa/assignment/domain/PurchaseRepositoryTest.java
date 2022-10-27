package com.musinsa.assignment.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class PurchaseRepositoryTest {

    @Autowired
    PurchaseRepository purchaseRepository;

    @Test
    @DisplayName("구매 정보 조회")
    void selectPurchase() {

        Optional<Purchase> byPurchaseNo = purchaseRepository.findByPurchaseNo(1L);
        Purchase purchase = byPurchaseNo.orElseThrow(IllegalArgumentException::new);
        List<OrderProduct> orderProductList = purchase.getOrderProductList();

        assertEquals(48000, purchase.getProductAmount());
        assertEquals(2500, purchase.getDeliveryAmount());
        assertEquals(50500, purchase.getTotalAmount());

        OrderProduct orderProduct1 = orderProductList.get(0);
        assertEquals(1L, orderProduct1.getOrderNo());
        assertEquals(1L, orderProduct1.getProductNo());
        assertEquals("신발A", orderProduct1.getProductName());
        assertEquals(15000, orderProduct1.getProductAmount());
        assertEquals(1, orderProduct1.getProductQuantity());

        OrderProduct orderProduct2 = orderProductList.get(1);
        assertEquals(2L, orderProduct2.getOrderNo());
        assertEquals(2L, orderProduct2.getProductNo());
        assertEquals("신발B", orderProduct2.getProductName());
        assertEquals(16000, orderProduct2.getProductAmount());
        assertEquals(1, orderProduct2.getProductQuantity());

        OrderProduct orderProduct3 = orderProductList.get(2);
        assertEquals(3L, orderProduct3.getOrderNo());
        assertEquals(3L, orderProduct3.getProductNo());
        assertEquals("신발C", orderProduct3.getProductName());
        assertEquals(17000, orderProduct3.getProductAmount());
        assertEquals(1, orderProduct3.getProductQuantity());

        DeliveryPolicy deliveryPolicy = purchase.getDeliveryPolicy();
        assertEquals(2, deliveryPolicy.getDeliveryPolicyNo());
        assertEquals(2500, deliveryPolicy.getOneWayDeliveryFee());
        assertEquals("N", deliveryPolicy.getDeliveryFeeSupportYn());
    }
}