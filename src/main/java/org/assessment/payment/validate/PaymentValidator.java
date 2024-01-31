package org.assessment.payment.validate;

import org.assessment.payment.dto.FeePaymentDto;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.assessment.payment.util.FeeUtils;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.assessment.payment.enums.ErrorCode.GRADE_NOT_FOUND;
import static org.assessment.payment.enums.ErrorCode.SCHOOL_NOT_FOUND;
import static org.assessment.payment.enums.ErrorCode.STUDENT_NOT_FOUND;

@Component
public class PaymentValidator {

    public boolean validateFeePaymentRequest(FeePaymentDto dto){
        Optional.ofNullable(dto).orElseThrow(() -> new ApplicationException(ErrorCode.VALUE_REQ.getCode(),String.format(ErrorCode.VALUE_REQ.getMessage(), "body") ));
        Optional.ofNullable(dto.getCardNo()).filter(FeeUtils::isValidCardNumber)
                .orElseThrow(() -> new ApplicationException(ErrorCode.VALUE_REQ.getCode(),
                        String.format(ErrorCode.VALUE_REQ.getMessage(), "card_no is invalid or ")));

        Optional.ofNullable(dto.getFeeType())
                .orElseThrow(() -> new ApplicationException(ErrorCode.VALUE_REQ.getCode(),
                        String.format(ErrorCode.VALUE_REQ.getMessage(), "fee_type")));
        return true;
    }

    public boolean validateEnrolledStudent(StudentDto studentDto){
        Optional.ofNullable(studentDto)
                .orElseThrow(() -> new ApplicationException(STUDENT_NOT_FOUND));
        Optional.ofNullable(studentDto.getGrade())
                .orElseThrow(() -> new ApplicationException(GRADE_NOT_FOUND));
        Optional.ofNullable(studentDto.getGrade().getSchool())
                .orElseThrow(() -> new ApplicationException(SCHOOL_NOT_FOUND));
        return true;
    }
}
