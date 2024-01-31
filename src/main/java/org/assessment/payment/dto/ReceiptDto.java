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
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class ReceiptDto implements Serializable {

    @JsonProperty("receiptNumber")
    private String receiptNumber;
    @JsonProperty("transactionId")
    private String transactionId;
    @JsonProperty("schoolUuid")
    private String schoolUuid;
    @JsonProperty("schoolName")
    private String schoolName;
    @JsonProperty("studentUuid")
    private String studentUuid;
    @JsonProperty("studentId")
    private String studentId;
    @JsonProperty("studentFirstName")
    private String studentFirstName;
    @JsonProperty("studentLastName")
    private String studentLastName;
    @JsonProperty("guardianName")
    private String guardianName;
    @JsonProperty("totalAmount")
    private BigDecimal totalAmount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("cardNumber")
    private String cardNumber;
    @JsonProperty("cardNetwork")
    private String cardNetwork;
    @JsonProperty("feePaidAt")
    private LocalDateTime feePaidAt;
    List<ReceiptDetailDto> receiptDetails;
}
