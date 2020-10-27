package com.mindex.challenge;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

public class SampleData {

    public static final String testId = "1234";

    public static Employee testEmployee = new Employee(testId,"","","","", Collections.emptyList());

    public static final Employee testReturnedEmployee = new Employee(testId,"John","Doe","Developer","Engineering", Collections.emptyList());

    public static final Compensation testCompensation = new Compensation(testEmployee,new BigDecimal(70000.0), LocalDate.now());

    public static final Compensation testExpectedCompensation = new Compensation(testReturnedEmployee,new BigDecimal(70000.0), LocalDate.now());

}
