package com.musinsa.assignment.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@Data
@Table
@Entity
public class OrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderNo;

    @NotNull
    @Column
    private long productNo;

    @NotNull
    @Column
    private String productName;

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
    @JoinColumn(name = "purchase_no")
    private Purchase purchase;

}
