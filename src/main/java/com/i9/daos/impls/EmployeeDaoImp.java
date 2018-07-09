package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.models.Employee;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {

    private BaseDao baseDao;

    @Override
    public List<Employee> getAll() {
        List<Employee> employeeList = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            resultSet = baseDao.searchQuery("select * from Employee");
            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setWorkloadHours(resultSet.getInt("workloadHours"));
                employee.setName(resultSet.getString("name"));
                employeeList.add(employee);
            }
        }catch (SQLException e){
            System.out.println("Error while searching on employee Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }
        return employeeList;
    }

    @Override
    public Employee getEmployee(String name) {
        Employee employee = new Employee();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Employee AS x WHERE x.name = '" + name + "';");
            resultSet.next();
            employee.setName(name);
            employee.setWorkloadHours(resultSet.getInt("workloadHours"));
        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        return employee;
    }

    @Override
    public boolean save(Employee object) {
        return false;
    }

    @Override
    public boolean delete(Employee object) {
        return false;
    }

    @Override
    public boolean update(Employee object) {
        return false;
    }

    @Required
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
