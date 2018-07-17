package com.i9.services;

import com.i9.models.Task;

import java.util.List;

public interface TaskService {
    List<Task> getTasksByPhase(int phaseId);
    Boolean saveTask(Task task);
}
