package com.i9.services.impls;

import com.i9.daos.EmployeeDao;
import com.i9.models.Employee;
import com.i9.services.EmployeeService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class EmployeeServiceImp implements EmployeeService {

    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployees() {
        return employeeDao.getAll();
    }

    @Required
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
