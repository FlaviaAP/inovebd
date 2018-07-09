package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.daos.EmployeeHoursPerDayDao;
import com.i9.models.Employee;
import com.i9.models.EmployeeHoursPerDay;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeHoursPerDayDaoImp implements EmployeeHoursPerDayDao {

    private BaseDao baseDao;
    private EmployeeDao employeeDao;

    @Override
    public List<EmployeeHoursPerDay> getEmployeesHoursPerDayByPhase(int phaseId) {
        List<EmployeeHoursPerDay> employeesHoursPerDay = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM PhaseEmployee AS x WHERE x.phaseId = " + String.valueOf(phaseId) + ";");

            while(resultSet.next()) {
                EmployeeHoursPerDay employeeHoursPerDay = new EmployeeHoursPerDay();
                employeeHoursPerDay.setHoursPerDay(resultSet.getInt("hoursPerDay"));
                Employee employee = employeeDao.getEmployee(resultSet.getString("employeeName"));
                employeeHoursPerDay.setEmployee(employee);

                employeesHoursPerDay.add(employeeHoursPerDay);
            }

        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        return employeesHoursPerDay;
    }

    @Override
    public List<EmployeeHoursPerDay> getAll() {
        return null;
    }

    @Override
    public boolean save(EmployeeHoursPerDay object) {
        return false;
    }

    @Override
    public boolean delete(EmployeeHoursPerDay object) {
        return false;
    }

    @Override
    public boolean update(EmployeeHoursPerDay object) {
        return false;
    }

    @Required
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

    @Required
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }
}
