package com.i9.daos.impls;

import com.i9.daos.BaseDao;
import com.i9.daos.ClientDao;
import com.i9.models.Client;
import org.springframework.beans.factory.annotation.Required;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientDaoImp implements ClientDao {

    private BaseDao baseDao;

    @Override
    public List<Client> getAll() {
        List<Client> clientList = new ArrayList<>();
        ResultSet resultSet = null;
        try{
            resultSet = baseDao.searchQuery("SELECT * FROM Client");
            while(resultSet.next()){
                String CNPJCPF = resultSet.getString("CNPJCPF");
                String name = resultSet.getString("name");
                String type = resultSet.getString("type");
                Client client = new Client(CNPJCPF, name, type);
                clientList.add(client);
            }
        }catch (SQLException e){
            System.out.println("Error while searching on employee Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);

        }
        return clientList;
    }

    @Override
    public Client getClient(String CNPJCPF) {
        Client client = new Client();
        ResultSet resultSet = null;
        try {
            resultSet = baseDao.searchQuery("SELECT * FROM Client AS data WHERE data.CNPJCPF = '" + CNPJCPF + "';");
            resultSet.next();
            client.setName(resultSet.getString("name"));
            client.setCNPJCPF(CNPJCPF);
            client.setType(resultSet.getString("type"));
        } catch (SQLException e){
            System.out.println("Error while searching on project Table");
            e.printStackTrace();
        }
        finally {
            baseDao.closeQuery(resultSet);
        }

        return client;
    }

    @Override
    public boolean save(Client object) {
        return false;
    }

    @Override
    public boolean delete(Client object) {
        return false;
    }

    @Override
    public boolean update(Client object) {
        return false;
    }

    @Required
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }

}
