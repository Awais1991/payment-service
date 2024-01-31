package org.assessment.payment.commen;

/**
 * @author Awais
 * @created 1/23/2024 - 5:53 PM
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
public class ApiResponse<T> {

	private T data;
	private ErrorInfo error;
}
