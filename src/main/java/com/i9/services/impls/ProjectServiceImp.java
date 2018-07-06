package com.i9.services.impls;

import com.i9.daos.ProjectDao;
import com.i9.models.Project;
import com.i9.services.ProjectService;
import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class ProjectServiceImp implements ProjectService {

    ProjectDao projectDao;

    @Override
    public List<Project> getProjects() {
        List<Project> projectList = new ArrayList<>();
        projectList = projectDao.getAll();
        return projectList;
    }

    @Override
    public Project getProject(int id) {
        Project project = new Project();
        project = projectDao.getProject(id);
        return project;
    }

    @Required
    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

}
