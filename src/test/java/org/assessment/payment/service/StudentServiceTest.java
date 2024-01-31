package org.assessment.payment.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.assessment.payment.connector.StudentConnector;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.assessment.payment.validate.PaymentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class StudentServiceTest {

    @Mock
    private StudentConnector studentConnector;

    @Mock
    private PaymentValidator  paymentValidator;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetEnrolledStudentSuccess() {
        String validRollNo = "33256";
        StudentDto expectedStudentDto = createSampleStudentDto();

        when(studentConnector.getEnrolledStudent(validRollNo)).thenReturn(expectedStudentDto);
        when(paymentValidator.validateEnrolledStudent(expectedStudentDto)).thenReturn(true);

        StudentDto resultStudentDto = studentService.getEnrolledStudent(validRollNo);
        assertNotNull(resultStudentDto);
        assertEquals(expectedStudentDto, resultStudentDto);
    }

    @Test
    public void testGetEnrolledStudentNotFound() {
        String validRollNo = "123";

        when(studentConnector.getEnrolledStudent(validRollNo))
                .thenThrow(new ApplicationException(ErrorCode.STUDENT_NOT_FOUND));

        ApplicationException exception =  assertThrows(ApplicationException.class, () ->
                studentService.getEnrolledStudent(validRollNo));
        assertEquals(exception.getMessage(), "student not found");
        assertEquals(exception.getCode(), "300103");
    }

    private StudentDto createSampleStudentDto() {
        return new StudentDto();
    }
}

