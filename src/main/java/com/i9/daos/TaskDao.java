package com.i9.daos;

import com.i9.models.Task;

import java.util.List;

public interface TaskDao extends GenericDao<Task> {
    List<Task> getTasksByPhase(int id);
}
