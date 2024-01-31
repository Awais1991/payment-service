package org.assessment.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class FeePaymentDto implements Serializable {

     @JsonProperty("studentRollNo")
     private String studentRollNo;
     @JsonProperty("feeType")
     private String feeType;
     @JsonProperty("cardNo")
     private String cardNo;
}
