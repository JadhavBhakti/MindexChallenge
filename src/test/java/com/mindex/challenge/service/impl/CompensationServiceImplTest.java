package com.mindex.challenge.service.impl;

import com.mindex.challenge.SampleData;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CompensationServiceImplTest {

    @InjectMocks
    private CompensationServiceImpl compensationServiceImpl;

    @Mock
    private CompensationRepository compensationRepository;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void createCompensationTest() {
        when(employeeRepository.findByEmployeeId(SampleData.testId)).thenReturn(SampleData.testReturnedEmployee);

        Compensation testActualCompensation = compensationServiceImpl.createCompensation(SampleData.testCompensation);

        assertNotNull(testActualCompensation);
        assertNotNull(testActualCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(SampleData.testExpectedCompensation, testActualCompensation);
    }

    @Test
    void readCompensationTest() {
        when(compensationRepository.findByEmployeeEmployeeId(SampleData.testId)).thenReturn(SampleData.testExpectedCompensation);

        Compensation testActualCompensation = compensationServiceImpl.readCompensation(SampleData.testId);

        assertEquals(SampleData.testId, SampleData.testExpectedCompensation.getEmployee().getEmployeeId());
        assertCompensationEquivalence(SampleData.testExpectedCompensation, testActualCompensation);
    }

    private static void assertCompensationEquivalence(Compensation expected, Compensation actual) {
        assertEquals(expected.getEmployee().getEmployeeId(), actual.getEmployee().getEmployeeId());
        assertEquals(expected.getEmployee().getFirstName(), actual.getEmployee().getFirstName());
        assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName());
        assertEquals(expected.getEmployee().getDepartment(), actual.getEmployee().getDepartment());
        assertEquals(expected.getEmployee().getPosition(), actual.getEmployee().getPosition());
        assertEquals(expected.getSalary(), actual.getSalary());
        assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
    }
}