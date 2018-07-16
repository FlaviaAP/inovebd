package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.daos.EmployeeHoursPerDayDao;
import com.i9.models.Employee;
import com.i9.models.DailyHours;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeHoursPerDayDaoImp implements EmployeeHoursPerDayDao {

    private BaseDao baseDao;
    private EmployeeDao employeeDao;

    @Override
    public List<DailyHours> getEmployeesHoursPerDayByPhase(int phaseId) {
        List<DailyHours> employeesHoursPerDay = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM PhaseEmployee AS x WHERE x.phaseId = " + String.valueOf(phaseId) + ";");

            employeesHoursPerDay = buildEmployeesHoursPerDayByResultSet(resultSet);

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
    public List<DailyHours> getEmployeesHoursPerDayByTask(int taskId) {
        List<DailyHours> employeesHoursPerDay = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM EmployeeTask AS x WHERE x.taskId = " + String.valueOf(taskId) + ";");

            employeesHoursPerDay = buildEmployeesHoursPerDayByResultSet(resultSet);

        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        return employeesHoursPerDay;
    }

    private List<DailyHours> buildEmployeesHoursPerDayByResultSet(ResultSet resultSet) throws SQLException {
        HashMap<String, DailyHours> dailyHoursMap = new HashMap<>();
        while(resultSet.next()){
            Employee employee = employeeDao.getEmployee(resultSet.getString("employeeName"));

            Double hours;
            for (DailyHours dailyHours: employee.getDailyHours()) {
                hours = (dailyHours.getDailyWorkload() * (resultSet.getDouble("percentageOfDailyHours")/100));

                if(dailyHoursMap.containsKey(dailyHours.getDay())) {
                    hours += dailyHoursMap.get(dailyHours.getDay()).getDailyWorkload();
                }
                dailyHours.setDailyWorkload(hours);

                dailyHoursMap.put(dailyHours.getDay(), dailyHours);
            }
        }
        return new ArrayList<>(dailyHoursMap.values());
    }

    @Override
    public List<DailyHours> getAll() {
        return null;
    }

    @Override
    public boolean save(DailyHours object) {
        return false;
    }

    @Override
    public boolean delete(DailyHours object) {
        return false;
    }

    @Override
    public boolean update(DailyHours object) {
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
