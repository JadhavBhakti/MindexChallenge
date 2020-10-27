package com.mindex.challenge.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

public class Compensation {
    private Employee employee;
    private BigDecimal salary;
    /**
     * Can use ZonedDateTime if requirement is to have time and Zone ID along with Date
     * Use this json format while hitting the REST ENDPOINT : "effectiveDate": "2020-01-01T00:00:00"
     */
    private LocalDate effectiveDate;

    public Compensation() {
    }

    public Compensation(Employee employee, BigDecimal salary, LocalDate effectiveDate) {
        this.employee = employee;
        this.salary = salary;
        this.effectiveDate = effectiveDate;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
