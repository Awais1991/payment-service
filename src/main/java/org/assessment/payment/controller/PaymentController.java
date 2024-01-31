package org.assessment.payment.controller;

import org.assessment.payment.commen.ApiResponse;
import org.assessment.payment.dto.FeePaymentDto;
import org.assessment.payment.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/payments")
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping
	public ResponseEntity<ApiResponse<String>> addFee(@RequestBody FeePaymentDto dto){
		return ResponseEntity.ok(new ApiResponse<>(paymentService.collectStudentFee(dto), null));
	}

}
