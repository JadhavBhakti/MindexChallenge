package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompensationServiceImpl implements CompensationService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository ;

    @Autowired
    private CompensationRepository compensationRepository;

    /**
     * This method takes Compensation object as input.
     * The Employee object of Compensation consists of EmployeeId and other details are fetched from employee collection.
     * The fetched Employee object is saved in Compensation object along with salary and effectiveDate.
     * Finally, the Compensation object is stored in compensation collection.
     * @param compensation {Compensation}
     * @return {Compensation}
     */
    @Override
    public Compensation createCompensation(Compensation compensation) {
        LOG.debug("Creating compensation [{}]", compensation);

        Employee employee = employeeRepository.findByEmployeeId(compensation.getEmployee().getEmployeeId());
        compensation.setEmployee(employee);
        compensationRepository.insert(compensation);

        return compensation;
    }

    /**
     * This method takes employeeId as and input id.
     * The entry corresponding to the employeeId is fetched from compensation collection and returned.
     * @param id {String}
     * @return {Compensation}
     */
    @Override
    public Compensation readCompensation(String id) {
        LOG.debug("Reading compensation with id [{}]", id);

        Compensation compensation = compensationRepository.findByEmployeeEmployeeId(id);

        if (compensation == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return compensation;
    }
}
