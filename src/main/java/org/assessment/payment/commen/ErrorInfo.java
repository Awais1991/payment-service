package org.assessment.payment.commen;

/**
 * @author Awais
 * @created 1/24/2024 - 11:38 PM
 * @project demo-assessment
 */

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
public class ErrorInfo {

	private String code;
	private String message;


}
