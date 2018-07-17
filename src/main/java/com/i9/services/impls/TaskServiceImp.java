package com.i9.services.impls;

import com.i9.daos.TaskDao;
import com.i9.models.Task;
import com.i9.services.TaskService;
import org.springframework.beans.factory.annotation.Required;

import java.util.List;

public class TaskServiceImp implements TaskService{

    private TaskDao taskDao;

    @Override
    public List<Task> getTasksByPhase(int phaseId) {
        List<Task> tasks = taskDao.getTasksByPhase(phaseId);
        return tasks;
    }

    @Required
    public void setTaskDao(TaskDao taskDao) { this.taskDao = taskDao; }

    @Override
    public Boolean saveTask(Task task) {
        task.setStatusPercent(0);
        task.setStatusTag("Not Initialized");
        return taskDao.save(task);
    }
}
