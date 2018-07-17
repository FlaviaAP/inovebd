package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.EmployeeHoursPerDayDao;
import com.i9.daos.TaskDao;
import com.i9.models.DailyHours;
import com.i9.models.EmployeeAssignedToTask;
import com.i9.models.Task;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImp implements TaskDao {

    private BaseDao baseDao;
    private EmployeeHoursPerDayDao employeeHoursPerDayDao;

    @Override
    public List<Task> getTasksByPhase(int phaseId) {
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Task AS data WHERE data.phaseId = " + String.valueOf(phaseId) + ";");
            while (resultSet.next()) {
                Task task = new Task();
                int taskId = resultSet.getInt("id");
                task.setName(resultSet.getString("name"));
                task.setId(taskId);
                task.setPhaseId(phaseId);
                task.setRealHours(resultSet.getInt("realHours"));
                if(resultSet.getString("realEndDate") != null)
                    task.setRealEndDate(LocalDate.parse(resultSet.getString("realEndDate"),DateTimeFormatter.ofPattern("yyyy-LL-dd")));
                task.setPlatform(resultSet.getString("platform"));
                task.setFunctionalityTag(resultSet.getString("functionalityTag"));
                if(resultSet.getString("initialDate")!= null)
                    task.setInitialDate(LocalDate.parse(resultSet.getString("initialDate"),DateTimeFormatter.ofPattern("yyyy-LL-dd")));
                task.setHourEstimation(resultSet.getInt("hourEstimation"));
                task.setResponsibleEmployee(resultSet.getString("responsibleEmployee"));
                task.setStatusPercent(resultSet.getInt("statusPercent"));
                task.setStatusTag(resultSet.getString("statusTag"));

                List<DailyHours> employeesHoursPerDay = employeeHoursPerDayDao.getEmployeesHoursPerDayByTask(taskId);
                task.setDailyHours(employeesHoursPerDay);
                List<EmployeeAssignedToTask> employeesAssignedToTask = employeeHoursPerDayDao.getEmployeesAssignedToTask(taskId);
                task.setEmployeesAssignedToTask(employeesAssignedToTask);

                task.calculateEstimation();

                tasks.add(task);
            }

        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }
        return tasks;
    }

    @Override
    public List<Task> getAll() {
        return null;
    }

    @Override
    public boolean save(Task task) {
        try{
            ResultSet resultSet = baseDao.searchQuery("SELECT max(id)+1 from Task");
            resultSet.next();
            task.setId(resultSet.getInt(1));
        }catch (SQLException e){

        }
        try{
            baseDao.transactionQuery("INSERT INTO Functionality values('"+ task.getFunctionalityTag() +"');");
        }catch (SQLException e){

        }
        try {
            String sql = "INSERT INTO Task (id, name, phaseId, platform, functionalityTag, initialDate, hourEstimation, responsibleEmployee, statusPercent, statusTag)\n" +
                    "SELECT max(id)+1 , '" + task.getName()+"', "+task.getPhaseId()+", '"+task.getPlatform()+"', '"+task.getFunctionalityTag()+"', " +
                    "STR_TO_DATE('"+task.getInitialDate().format(DateTimeFormatter.ofPattern("dd-LL-yyyy")) +"', '%d-%m-%Y'), "+task.getHourEstimation()+", '"+task.getResponsibleEmployee()+"', " +
                    task.getStatusPercent()+", '"+task.getStatusTag()+"' from Task;";
            baseDao.transactionQuery(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            for(EmployeeAssignedToTask employeeAssignedToTask: task.getEmployeesAssignedToTask()){
                baseDao.transactionQuery("INSERT INTO EmployeeTask (employeeName, taskId, percentageOfDailyHours) VALUES ('"+employeeAssignedToTask.getName()+"', "+ task.getId()+", "+employeeAssignedToTask.getDailyHoursPercentage() + ");");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean delete(Task object) {
        return false;
    }

    @Override
    public boolean update(Task object) {
        return false;
    }

    @Required
    public void setBaseDao(BaseDao baseDao) { this.baseDao = baseDao; }

    @Required
    public void setEmployeeHoursPerDayDao(EmployeeHoursPerDayDao employeeHoursPerDayDao) { this.employeeHoursPerDayDao = employeeHoursPerDayDao; }

}
