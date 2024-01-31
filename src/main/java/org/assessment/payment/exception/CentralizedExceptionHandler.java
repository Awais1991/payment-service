package org.assessment.payment.exception;

import org.assessment.payment.commen.ApiResponse;
import org.assessment.payment.commen.ErrorInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Awais
 * @created 1/23/2024 - 5:52 PM
 * @project demo-assessment
 */

@ControllerAdvice
public class CentralizedExceptionHandler {

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApiResponse<Void>> handleApplicationException(ApplicationException ex){
		ApiResponse<Void> voidApiResponse = new ApiResponse<>(null, new ErrorInfo(ex.getCode(), ex.getMessage()));
		return ResponseEntity.badRequest().body(voidApiResponse);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleApplicationException(Exception ex){
		ApiResponse<Void> voidApiResponse = new ApiResponse<>(null, new ErrorInfo("100999", ex.getMessage()));
		return ResponseEntity.badRequest().body(voidApiResponse);
	}
}
