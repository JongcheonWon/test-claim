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
public class ClaimProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long claimProductNo;

    @NotNull
    @Column
    private long productNo;

    @NotNull
    @Column
    private int productAmount;

    @NotNull
    @Column
    private int productQuantity;

    @NotNull
    @Column
    private LocalDateTime changeDatetime;

    @NotNull
    @Column
    private LocalDateTime registerDatetime;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "claim_no")
    private Claim claim;
}
