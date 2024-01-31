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

@Service
public class FallBackDocumentService {

    private final DocumentConnector  documentConnector;

    public FallBackDocumentService(DocumentConnector documentConnector) {
        this.documentConnector = documentConnector;
    }

    public String generateReceipt(FeeTransaction feeTransaction){
       return feeTransaction.getTransactionId();
    }
}
