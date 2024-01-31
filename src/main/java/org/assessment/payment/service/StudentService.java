package org.assessment.payment.service;

import org.assessment.payment.connector.StudentConnector;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.validate.PaymentValidator;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentConnector studentConnector;
    private final PaymentValidator paymentValidator;

    public StudentService(StudentConnector studentConnector, PaymentValidator paymentValidator) {
        this.studentConnector = studentConnector;
        this.paymentValidator = paymentValidator;
    }

    public StudentDto getEnrolledStudent(String rollNo){
        StudentDto studentDto =  studentConnector.getEnrolledStudent(rollNo);
        paymentValidator.validateEnrolledStudent(studentDto);
        return studentDto;
    }

}
