package com.musinsa.assignment.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Table
@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long purchaseNo;

    @Column
    private int totalAmount;

    @Column
    private int productAmount;

    @Column
    private int deliveryAmount;

    @OneToMany(mappedBy = "purchase", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderProduct> orderProductList;

    @NotNull
    @OneToOne
    @JoinColumn(name = "delivery_policy_no")
    private DeliveryPolicy deliveryPolicy;
}
