package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeDao;
import com.i9.models.DailyHours;
import com.i9.models.Employee;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EmployeeDaoImp implements EmployeeDao {

    private BaseDao baseDao;

    @Override
    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            resultSet = baseDao.searchQuery("select * from Employee");
            while(resultSet.next()){
                Employee employee = convertFromResultSetToEmployee(resultSet);
                employees.add(employee);
            }
        }catch (SQLException e){
            System.out.println("Error while searching on employee Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

//        try {
//            setDailyHoursToEmployees(employees);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return employees;
    }

    @Override
    public Employee getEmployee(String name) {
        Employee employee = null;
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Employee AS data WHERE data.name = '" + name + "';");
            resultSet.next();
            employee = convertFromResultSetToEmployee(resultSet);
        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        try {
            setDailyHoursToEmployees(employees);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employees.get(0);
    }

    private Employee convertFromResultSetToEmployee(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setName(resultSet.getString("name"));
        return employee;
    }

    private void setDailyHoursToEmployees(List<Employee> employees) throws SQLException {
        for(Employee employee: employees){
            HashMap<String,DailyHours> dailyHoursMap = new HashMap<>();
            ResultSet workloadResult = baseDao.searchQuery("SELECT * from EmployeeDaysOfWeek where employeeName='"+ employee.getName() +"' ORDER BY dayOfWeek");
            while(workloadResult.next()){
                String dayOfWeek = workloadResult.getString("dayOfWeek");
                Double workloadHours = (workloadResult.getDouble("leaveHour") + workloadResult.getDouble("leaveMinutes")/60) -
                        (workloadResult.getDouble("entryHour") + workloadResult.getDouble("entryMinutes")/60);
                DailyHours dailyHours = null;
                if(dailyHoursMap.containsKey(dayOfWeek)) {
                    dailyHours = dailyHoursMap.get(dayOfWeek);
                    dailyHours.setDailyWorkload(dailyHours.getDailyWorkload() + workloadHours);
                }
                else{
                    dailyHours = new DailyHours();
                    dailyHours.setDailyWorkload(workloadHours);
                    dailyHours.setDay(dayOfWeek);
                }
                dailyHoursMap.put(dayOfWeek,dailyHours);

            }
            employee.setDailyHours(new ArrayList<>(dailyHoursMap.values()));
            workloadResult.close();
        }

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
