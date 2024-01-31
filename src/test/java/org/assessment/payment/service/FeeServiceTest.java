package org.assessment.payment.service;

import org.assessment.payment.connector.FeeConnector;
import org.assessment.payment.dto.FeeDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class FeeServiceTest {

    @Mock
    private FeeConnector feeConnector;

    @InjectMocks
    private FeeService feeService;
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testGetStudentFeeSuccess() {
        // Arrange
        String validGrade = "10";
        List<FeeDto> expectedFeeList = Arrays.asList(createSampleFeeDto(), createSampleFeeDto());

        when(feeConnector.getFeeByFeeTypeAndGrade(validGrade)).thenReturn(expectedFeeList);

        // Act
        List<FeeDto> resultFeeList = feeService.getStudentFee(validGrade);

        // Assert
        assertNotNull(resultFeeList);
        assertEquals(expectedFeeList, resultFeeList);
    }

    @Test
    public void testGetStudentFeeEmptyList() {
        // Arrange
        String validGrade = "10";
        List<FeeDto> emptyFeeList = Arrays.asList();

        when(feeConnector.getFeeByFeeTypeAndGrade(validGrade)).thenReturn(emptyFeeList);

        // Act
        List<FeeDto> resultFeeList = feeService.getStudentFee(validGrade);

        // Assert
        assertNotNull(resultFeeList);
        assertEquals(emptyFeeList, resultFeeList);
    }

    private FeeDto createSampleFeeDto() {
        return new FeeDto();
    }
}

