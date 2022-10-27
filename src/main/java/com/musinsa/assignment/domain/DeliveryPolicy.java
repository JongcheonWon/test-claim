package com.musinsa.assignment.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table
@Entity
public class DeliveryPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long deliveryPolicyNo;

    @Column
    private String deliveryFeeSupportYn;

    @Column
    private int oneWayDeliveryFee;

    @NotNull
    @Column
    private LocalDateTime changeDatetime;

    @NotNull
    @Column
    private LocalDateTime registerDatetime;
}
