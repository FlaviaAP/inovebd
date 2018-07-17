package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.ProjectDao;
import com.i9.models.Project;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProjectDaoImp implements ProjectDao {

    private BaseDao baseDao;

    @Override
    public List<Project> getAll() {
        List<Project> projectList = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            resultSet = baseDao.searchQuery("SELECT * FROM Project");
            while(resultSet.next()){
                String name = resultSet.getString("name");
                String CNPJCPF = resultSet.getString("CNPJCPF");
                int id = resultSet.getInt("id");
                Project project = new Project(id, name, CNPJCPF);
                projectList.add(project);
            }
        }catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }
        return projectList;
    }

    @Override
    public Project getProject(int id) {
        Project project = new Project();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Project AS data WHERE data.id = " + String.valueOf(id) + ";");
            resultSet.next();
            project.setName(resultSet.getString("name"));
            project.setCNPJCPF(resultSet.getString("CNPJCPF"));
            project.setId(id);
        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        return project;
    }

    @Override
    public boolean save(Project object) {
        return false;
    }

    @Override
    public boolean delete(Project object) {
        return false;
    }

    @Override
    public boolean update(Project object) {
        return false;
    }

    @Required
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
