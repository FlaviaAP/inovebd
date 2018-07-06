package com.i9.daos;

import com.i9.daos.GenericDao;
import com.i9.models.Employee;
import com.i9.models.Project;

public interface ProjectDao extends GenericDao<Project> {
    public Project getProject(int id);
}
