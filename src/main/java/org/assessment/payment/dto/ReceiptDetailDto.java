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

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ReceiptDetailDto implements Serializable {

    @JsonProperty("feeUuid")
    private String feeUuid;
    @JsonProperty("feeType")
    private String feeType;
    @JsonProperty("feeTypeDesc")
    private String feeTypeDesc;
    @JsonProperty("feeAmount")
    private BigDecimal feeAmount;
    @JsonProperty("feeCurrency")
    private String feeCurrency;
    @JsonProperty("feeGrade")
    private String feeGrade;
}
