package org.assessment.payment.dto;

/**
 * @author Awais
 * @created 1/23/2024 - 5:37 PM
 * @project demo-assessment
 */

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
public class GradeDto implements Serializable {
	@JsonProperty("gradeId")
	private String uuid;
	@JsonProperty("grade")
	private String grade;
	@JsonProperty("school")
	private SchoolDto school;
}
