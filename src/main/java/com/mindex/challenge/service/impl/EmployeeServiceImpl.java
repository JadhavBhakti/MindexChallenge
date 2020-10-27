package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Reading employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }

    /**
     * Employee Details corresponding to employeeID i.e. id is fetched and stored in ReportingStructure object.
     * The number of Reports are calculated by using queue has the input id as the first entry.
     * Then the id is removed and corresponding reports are counted by increasing count variable by 1.
     * And simultaneously adding reports to the queue for further calculation.
     * The while loop gets executed till queue is empty and we get final count.
     * @param id {String}
     * @return {ReportingStructure}
     */
    @Override
    public ReportingStructure getStructure(String id) {
        LOG.debug("Fetching Reporting Structure employee [{}]", id);

        ReportingStructure reportingStructure = new ReportingStructure();
        int count = 0;
        Queue<String> queue = new LinkedList<>();
        queue.add(id);

        while(!queue.isEmpty()) {
            String employeeId = queue.remove();
            Employee employee = employeeRepository.findByEmployeeId(employeeId);
            if(employee.getDirectReports() != null) {
                for(Employee report : employee.getDirectReports()) {
                    count += 1;
                    queue.add(report.getEmployeeId());
                }
            }
        }

        reportingStructure.setEmployee(employeeRepository.findByEmployeeId(id));
        reportingStructure.setNumberOfReports(count);

        return reportingStructure;
    }

}
