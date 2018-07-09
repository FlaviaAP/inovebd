package com.i9.daos;

import com.i9.models.Employee;

public interface EmployeeDao extends GenericDao<Employee> {
    Employee getEmployee(String name);
}
