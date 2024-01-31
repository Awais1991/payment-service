package org.assessment.payment.enums;

/**
 * @author Awais
 * @created 1/24/2024 - 11:30 PM
 * @project demo-assessment
 */
public enum ErrorCode {
	VALUE_REQ("300101", "%s required"),
	CURRENCY_CURRENCY("300101", "currency not found"),
	FEE_NOT_FOUND("300102", "fee does not exit"),
	STUDENT_NOT_FOUND("300103", "student not found"),
	GRADE_NOT_FOUND("300104", "grade not found"),
	SCHOOL_NOT_FOUND("300105", "school not found"),
	TRANSACTION_NOT_FOUND("300106", "transaction not found"),
	DIFFERENT_FEE_CURRENCY_FOUND("300107", "transaction cannot be done due different fee currencies"),
	UNABLE_TO_GENERATE_RECEIPT("300107", "unable to generate the receipt"),
	;
	private String code;
	private String message;

	ErrorCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}
}
