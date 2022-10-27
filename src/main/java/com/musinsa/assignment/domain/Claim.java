package com.musinsa.assignment.domain;

import com.musinsa.assignment.claim.ClaimType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Table
@Entity
public class Claim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long claimNo;

    @NotNull
    @Column
    private Long purchaseNo;

    @Enumerated
    @NotNull
    @Column
    private ClaimType claimType;

    @NotNull
    @Column
    private LocalDateTime changeDatetime;

    @NotNull
    @Column
    private LocalDateTime registerDatetime;

    @OneToMany(mappedBy = "claim", cascade = CascadeType.ALL)
    private List<ClaimProduct> claimProductList = new ArrayList<>();

    @NotNull
    @OneToOne(mappedBy = "claim", cascade = CascadeType.ALL)
    private ClaimRefund claimRefund;

    public void addClaimProduct(ClaimProduct claimProduct) {
        claimProductList.add(claimProduct);
        claimProduct.setClaim(this);
    }

    public void addClaimRefund(ClaimRefund claimRefund) {
        this.claimRefund = claimRefund;
        claimRefund.setClaim(this);
    }
}
