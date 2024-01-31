package org.assessment.payment.service;

import org.assessment.payment.dto.FeeDto;
import org.assessment.payment.dto.FeePaymentDto;
import org.assessment.payment.dto.GradeDto;
import org.assessment.payment.dto.SchoolDto;
import org.assessment.payment.dto.StudentDto;
import org.assessment.payment.entity.FeeTransaction;
import org.assessment.payment.entity.FeeTransactionDetail;
import org.assessment.payment.enums.ErrorCode;
import org.assessment.payment.exception.ApplicationException;
import org.assessment.payment.repo.FeeTransactionRepository;
import org.assessment.payment.util.FeeUtils;
import org.assessment.payment.validate.PaymentValidator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final StudentService studentService;
    private final FeeService feeService;
    private final FeeTransactionRepository feeTransactionRepository;
    private final PaymentValidator paymentValidator;
    private final DocumentService documentService;

    public PaymentService(StudentService studentService, FeeService feeService,
                          FeeTransactionRepository feeTransactionRepository, PaymentValidator paymentValidator,
                          DocumentService documentService) {
        this.studentService = studentService;
        this.feeService = feeService;
        this.feeTransactionRepository = feeTransactionRepository;
        this.paymentValidator = paymentValidator;
        this.documentService = documentService;
    }

    public String collectStudentFee(FeePaymentDto dto){

        paymentValidator.validateFeePaymentRequest(dto);
        StudentDto enrolledUser = studentService.getEnrolledStudent(dto.getStudentRollNo());

        List<FeeDto> feeDto = feeService.getStudentFee(enrolledUser.getGrade().getGrade());

        BigDecimal totalAmount = getTotalAmount(feeDto);
        String currencyCode = validateAndGetCurrencyCode(feeDto);

        GradeDto gradeDto = enrolledUser.getGrade();
        SchoolDto schoolDto = gradeDto.getSchool();
        FeeTransaction feeTransaction = getFeeTransaction(dto, enrolledUser, totalAmount, currencyCode, schoolDto);

        Set<FeeTransactionDetail> feeTransactionDetails = getFeeTransactionDetails(feeDto, feeTransaction);
        feeTransaction.setFeeTransactionDetails(feeTransactionDetails);
        feeTransactionRepository.save(feeTransaction);
        documentService.generateReceipt(feeTransaction);

        return feeTransaction.getTransactionId();
    }

    private Set<FeeTransactionDetail> getFeeTransactionDetails(List<FeeDto> feeDto, FeeTransaction feeTransaction) {
        return feeDto.stream().map(feeDto1 -> FeeTransactionDetail.builder().feeAmount(feeDto1.getFeeAmount())
                .feeTransaction(feeTransaction).feeType(feeDto1.getFeeType())
                .feeTypeDesc(feeDto1.getFeeTypeDesc()).feeUuid(feeDto1.getFeeUUID())
                .feeCurrency(feeDto1.getFeeCurrency()).feeGrade(feeDto1.getFeeGrade()).build()).collect(Collectors.toSet());
    }

    private FeeTransaction getFeeTransaction(FeePaymentDto dto, StudentDto enrolledUser, BigDecimal totalAmount, String currencyCode, SchoolDto schoolDto) {
        return FeeTransaction.builder()
                .transactionId(FeeUtils.generateTransactionId(10)).studentId(dto.getStudentRollNo())
                .schoolUuid(schoolDto.getSchoolId())
                .schoolName(schoolDto.getSchoolName())
                .studentUuid(enrolledUser.getUuid())
                .studentFirstName(enrolledUser.getFirstName())
                .studentLastName(enrolledUser.getLastName())
                .guardianName(enrolledUser.getGuardianName())
                        .totalAmount(totalAmount).currency(currencyCode).cardNumber(dto.getCardNo())
                .cardNetwork(FeeUtils.detectCardNetwork(dto.getCardNo()).name()).feePaidAt(LocalDateTime.now()).build();
    }

    private BigDecimal getTotalAmount(List<FeeDto> feeDto) {
        return feeDto.stream().filter(feeDto1 -> null!=feeDto1.getFeeAmount())
                .map(FeeDto::getFeeAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private String validateAndGetCurrencyCode(List<FeeDto> feeDto) {
        if (feeDto.stream().filter(feeDto1 -> null!=feeDto1.getFeeCurrency())
                .map(FeeDto::getFeeCurrency).distinct().count() != 1){
            throw new ApplicationException(ErrorCode.DIFFERENT_FEE_CURRENCY_FOUND);
        }
        return feeDto.stream().filter(feeDto1 -> null!=feeDto1.getFeeCurrency())
                .map(FeeDto::getFeeCurrency).findAny().orElseThrow(() -> new ApplicationException(ErrorCode.CURRENCY_CURRENCY));
    }

}
