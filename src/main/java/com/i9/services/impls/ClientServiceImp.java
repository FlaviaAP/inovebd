package com.i9.services.impls;

import com.i9.daos.ClientDao;
import com.i9.models.Client;
import com.i9.services.ClientService;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.util.List;

public class ClientServiceImp implements ClientService {
    private ClientDao clientDao;

    @Override
    public List<Client> getClients() {
        return null;
    }

    @Override
    public Client getClient(String CNPJCPF) {
        return clientDao.getClient(CNPJCPF);
    }

    @Required
    public void setClientDao(ClientDao clientDao) {this.clientDao = clientDao; }
}
