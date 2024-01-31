package org.assessment.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class FeeDto implements Serializable {

    @JsonProperty("fee_unique_identifier")
    private String feeUUID;
    @JsonProperty("fee_type")
    private String feeType;
    @JsonProperty("fee_type_desc")
    private String feeTypeDesc;
    @JsonProperty("fee_grade")
    private String feeGrade;
    @JsonProperty("fee_amount")
    private BigDecimal feeAmount;
    @JsonProperty("fee_currency")
    private String feeCurrency;
    @JsonProperty("fee_frequency")
    private String feeFrequency;
    @JsonProperty("fee_effective_date")
    private LocalDate effectiveDate;
    @JsonProperty("fee_expiry_date")
    private LocalDate expiryDate;

}
