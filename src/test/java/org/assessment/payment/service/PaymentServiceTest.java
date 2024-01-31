package org.assessment.payment.service;

import org.assessment.payment.dto.FeeDto;
import org.assessment.payment.dto.FeePaymentDto;
import org.assessment.payment.dto.GradeDto;
import org.assessment.payment.dto.SchoolDto;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.entity.FeeTransaction;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.assessment.payment.repo.FeeTransactionRepository;
import org.assessment.payment.validate.PaymentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PaymentServiceTest {


    @Mock
    private PaymentValidator paymentValidator;

    @Mock
    private StudentService studentService;

    @Mock
    private FeeService feeService;

    @Mock
    private FeeTransactionRepository feeTransactionRepository;

    @Mock
    private DocumentService documentService;

    @InjectMocks
    private PaymentService paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetStudentFeeSuccess() {
        // Arrange
        FeePaymentDto validFeePaymentDto = createSampleFeePaymentDto();
        StudentDto enrolledUser = createSampleEnrolledUser();
        List<FeeDto> expectedFeeList = Arrays.asList(createSampleFeeDto(), createSampleFeeDto());

        when(paymentValidator.validateFeePaymentRequest(validFeePaymentDto)).thenReturn(true);
        when(studentService.getEnrolledStudent(validFeePaymentDto.getStudentRollNo())).thenReturn(enrolledUser);
        when(feeService.getStudentFee(enrolledUser.getGrade().getGrade())).thenReturn(expectedFeeList);
        when(feeTransactionRepository.save(any())).thenReturn(createSampleFeeTransaction());
        when(documentService.generateReceipt(any())).thenReturn(any());

        String resultTransactionId = paymentService.collectStudentFee(validFeePaymentDto);

        // Assert
        assertNotNull(resultTransactionId);
        assertTrue(resultTransactionId.length() > 0);

        // Verify interactions
        verify(paymentValidator, times(1)).validateFeePaymentRequest(validFeePaymentDto);
        verify(studentService, times(1)).getEnrolledStudent(validFeePaymentDto.getStudentRollNo());
        verify(feeService, times(1)).getStudentFee(enrolledUser.getGrade().getGrade());
        verify(feeTransactionRepository, times(1)).save(any());
        verify(documentService, times(1)).generateReceipt(any());
    }

    @Test
    public void testGetStudentFeeFailed() {
        // Arrange
        FeePaymentDto validFeePaymentDto = createSampleFeePaymentDto();
        when(paymentValidator.validateFeePaymentRequest(validFeePaymentDto))
                .thenThrow(new ApplicationException(ErrorCode.VALUE_REQ, String.format(ErrorCode.VALUE_REQ.getMessage(), "cardNo")));
        ApplicationException exception =  assertThrows(ApplicationException.class, () ->
                paymentService.collectStudentFee(validFeePaymentDto));
        assertEquals(exception.getMessage(), "cardNo required");
        assertEquals(exception.getCode(), "300101");

    }

    @Test
    public void testGetStudentFeeReceiptGenerationFailed() {
        // Arrange
        FeePaymentDto validFeePaymentDto = createSampleFeePaymentDto();
        StudentDto enrolledUser = createSampleEnrolledUser();
        List<FeeDto> expectedFeeList = Arrays.asList(createSampleFeeDto(), createSampleFeeDto());

        when(paymentValidator.validateFeePaymentRequest(validFeePaymentDto)).thenReturn(true);
        when(studentService.getEnrolledStudent(validFeePaymentDto.getStudentRollNo())).thenReturn(enrolledUser);
        when(feeService.getStudentFee(enrolledUser.getGrade().getGrade())).thenReturn(expectedFeeList);
        when(feeTransactionRepository.save(any())).thenReturn(createSampleFeeTransaction());
        when(documentService.generateReceipt(any())).thenThrow(new ApplicationException(ErrorCode.UNABLE_TO_GENERATE_RECEIPT));

        ApplicationException exception =  assertThrows(ApplicationException.class, () ->
                paymentService.collectStudentFee(validFeePaymentDto));
        assertEquals(exception.getMessage(), "unable to generate the receipt");
        assertEquals(exception.getCode(), "300107");

        // Verify interactions
        verify(paymentValidator, times(1)).validateFeePaymentRequest(validFeePaymentDto);
        verify(studentService, times(1)).getEnrolledStudent(validFeePaymentDto.getStudentRollNo());
        verify(feeService, times(1)).getStudentFee(enrolledUser.getGrade().getGrade());
        verify(feeTransactionRepository, times(1)).save(any());
        verify(documentService, times(1)).generateReceipt(any());

    }



    private FeePaymentDto createSampleFeePaymentDto() {
        FeePaymentDto feePaymentDto = new FeePaymentDto();
        feePaymentDto.setCardNo("5555555555554444");
        return feePaymentDto;
    }

    private StudentDto createSampleEnrolledUser() {
        GradeDto gradeDto = new GradeDto();
        gradeDto.setGrade("g1");
        SchoolDto schoolDto = new SchoolDto();
        schoolDto.setSchoolId("112233");
        schoolDto.setSchoolName("school");
        schoolDto.setSchoolLocation("location");
        gradeDto.setSchool(schoolDto);
        StudentDto studentDto = new StudentDto();
        studentDto.setGrade(gradeDto);
        return studentDto;
    }

    private FeeDto createSampleFeeDto() {
        FeeDto feeDto = new FeeDto();
        feeDto.setFeeCurrency("AED");
        feeDto.setFeeAmount(BigDecimal.ONE);
        return feeDto;
    }

    private FeeTransaction createSampleFeeTransaction() {
        return new FeeTransaction();
    }
}
