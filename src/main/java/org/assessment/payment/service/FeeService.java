package org.assessment.payment.service;

import org.assessment.payment.connector.FeeConnector;
import org.assessment.payment.dto.FeeDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeeService {

   private final FeeConnector feeConnector;

    public FeeService(FeeConnector feeConnector) {
        this.feeConnector = feeConnector;
    }

    public List<FeeDto> getStudentFee(String grade){
        return feeConnector.getFeeByFeeTypeAndGrade(grade);
    }
}
