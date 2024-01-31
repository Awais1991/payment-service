package org.assessment.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class SchoolDto {

	@JsonProperty("schoolId")
	private String schoolId;
	@JsonProperty("schoolName")
	private String schoolName;
	@JsonProperty("schoolLocation")
	private String schoolLocation;
}
