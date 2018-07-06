package com.i9.services;

import com.i9.daos.ProjectDao;
import com.i9.models.Project;

import java.util.List;

public interface ProjectService {

    List<Project> getProjects();

    Project getProject(int id);
}
