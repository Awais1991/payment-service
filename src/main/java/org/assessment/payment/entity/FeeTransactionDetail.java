package org.assessment.payment.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "fee_transactions_details")
public class FeeTransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fee_uuid", nullable = false)
    private String feeUuid;

    @Column(name = "fee_type", nullable = false)
    private String feeType;

    @Column(name = "fee_type_desc", nullable = false)
    private String feeTypeDesc;

    @Column(name = "fee_amount", nullable = false)
    private BigDecimal feeAmount;

    @Column(name = "fee_currency", nullable = false)
    private String feeCurrency;

    @Column(name = "fee_grade", nullable = false)
    private String feeGrade;

    @ManyToOne
    @JoinColumn(name = "transaction_id", referencedColumnName = "transaction_id")
    FeeTransaction feeTransaction;

}
