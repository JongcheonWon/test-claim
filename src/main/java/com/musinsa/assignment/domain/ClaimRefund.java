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
public class ClaimRefund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimRefundNo;

    @NotNull
    @Column
    private long claimDeliveryFee;

    @NotNull
    @Column
    private LocalDateTime changeDatetime;

    @NotNull
    @Column
    private LocalDateTime registerDatetime;

    @OneToOne
    @JoinColumn(name = "claim_no")
    private Claim claim;
}
