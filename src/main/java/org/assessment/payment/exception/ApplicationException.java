package org.assessment.payment.exception;


/**
 * @author Awais
 * @created 1/23/2024 - 5:47 PM
 * @project demo-assessment
 */

import org.assessment.payment.enums.ErrorCode;

public class ApplicationException extends RuntimeException {

	private final String code;
	public ApplicationException(String code, String message) {
		super(message);
		this.code = code;
	}

	public ApplicationException(ErrorCode errorCode, String message) {
		super(message);
		this.code = errorCode.getCode();
	}

	public ApplicationException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.code = errorCode.getCode();
	}

	public String getCode() {
		return code;
	}
}
