package org.assessment.payment.service;

import org.assessment.payment.connector.DocumentConnector;
import org.assessment.payment.dto.ReceiptDetailDto;
import org.assessment.payment.dto.ReceiptDto;
import org.assessment.payment.entity.FeeTransaction;
import org.assessment.payment.entity.FeeTransactionDetail;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    private final DocumentConnector  documentConnector;

    public DocumentService(DocumentConnector documentConnector) {
        this.documentConnector = documentConnector;
    }

    public String generateReceipt(FeeTransaction feeTransaction){
        // prepare data here
        //validate request before sending it

       ReceiptDto receiptDto =  Optional.ofNullable(feeTransaction)
               .map(this::mapToReceiptDto)
               .orElseThrow(() -> new ApplicationException(ErrorCode.TRANSACTION_NOT_FOUND));

        return documentConnector.generateReceipt(receiptDto);
    }

    private ReceiptDto mapToReceiptDto(FeeTransaction feeTransaction){
        ReceiptDto receiptDto = new ReceiptDto();
        receiptDto.setTransactionId(feeTransaction.getTransactionId());
        receiptDto.setSchoolUuid(feeTransaction.getSchoolUuid());
        receiptDto.setSchoolName(feeTransaction.getSchoolName());
        receiptDto.setStudentUuid(feeTransaction.getStudentUuid());
        receiptDto.setStudentId(feeTransaction.getStudentId());
        receiptDto.setStudentFirstName(feeTransaction.getStudentFirstName());
        receiptDto.setStudentLastName(feeTransaction.getStudentLastName());
        receiptDto.setGuardianName(feeTransaction.getGuardianName());
        receiptDto.setTotalAmount(feeTransaction.getTotalAmount());
        receiptDto.setCurrency(feeTransaction.getCurrency());
        receiptDto.setCardNumber(feeTransaction.getCardNumber());
        receiptDto.setCardNetwork(feeTransaction.getCardNetwork());
        receiptDto.setFeePaidAt(feeTransaction.getFeePaidAt());
        receiptDto.setReceiptDetails(feeTransaction.getFeeTransactionDetails().stream()
                .map(this::mapToDto).toList());
        return receiptDto;
    }

    private ReceiptDetailDto mapToDto(FeeTransactionDetail feeTransactionDetail){
        ReceiptDetailDto receiptDetailDto = new ReceiptDetailDto();
        receiptDetailDto.setFeeUuid(feeTransactionDetail.getFeeUuid());
        receiptDetailDto.setFeeType(feeTransactionDetail.getFeeType());
        receiptDetailDto.setFeeTypeDesc(feeTransactionDetail.getFeeTypeDesc());
        receiptDetailDto.setFeeAmount(feeTransactionDetail.getFeeAmount());
        receiptDetailDto.setFeeCurrency(feeTransactionDetail.getFeeCurrency());
        receiptDetailDto.setFeeGrade(feeTransactionDetail.getFeeGrade());
        return receiptDetailDto;

    }

}
