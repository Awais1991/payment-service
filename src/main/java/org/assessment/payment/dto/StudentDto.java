package org.assessment.payment.dto;

/**
 * @author Awais
 * @created 1/23/2024 - 5:37 PM
 * @project demo-assessment
 */

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class StudentDto implements Serializable {

	@JsonProperty("studentId")
	private String uuid;
	@JsonProperty("rollNumber")
	private String rollNo;
	@JsonProperty("mobileNumber")
	private String mobileNumber;
	@JsonProperty("guardianName")
	private String guardianName;
	@JsonProperty("guardianRelation")
	private String guardianRelation;
	@JsonProperty("firstName")
	private String firstName;
	@JsonProperty("lastName")
	private String lastName;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@JsonProperty("dateOfBirth")
	private LocalDate dateOfBirth;
	@JsonProperty("gender")
	private String gender;
	@JsonProperty("joiningDate")
	private LocalDate joiningDate;
	@JsonProperty("grade")
	private GradeDto grade;
}
