package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.TaskDao;
import com.i9.models.Task;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImp implements TaskDao {

    private BaseDao baseDao;

    @Override
    public List<Task> getTasksByPhase(int phaseId) {
        List<Task> tasks = new ArrayList<>();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Task AS x WHERE x.phaseId = " + String.valueOf(phaseId) + ";");
            while (resultSet.next()) {
                Task task = new Task();
                task.setId(resultSet.getInt("id"));
                task.setPhaseId(phaseId);
                task.setRealHours(resultSet.getInt("realHours"));
                task.setRealEndDate(resultSet.getDate("realEndDate"));
                task.setPlatform(resultSet.getString("platform"));
                task.setFunctionalityTag(resultSet.getString("functionalityTag"));
                task.setInitialDate(resultSet.getDate("initialDate"));
                task.setHourEstimation(resultSet.getInt("hourEstimation"));
                task.setStatus(resultSet.getInt("status"));
                task.setResponsibleEmployee(resultSet.getString("responsibleEmployee"));

                task.calculatePossibleEstimation();

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
    public boolean save(Task object) {
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
}
