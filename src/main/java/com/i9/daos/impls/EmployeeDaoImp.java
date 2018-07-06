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
            resultSet = baseDao.searchQuery("select * from func2");
            while(resultSet.next()){
                Employee employee = new Employee();
                employee.setAge(resultSet.getInt("func"));
                employee.setName(resultSet.getString("x"));
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
