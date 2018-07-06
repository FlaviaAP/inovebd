package com.i9.daos;

import com.i9.models.Client;

public interface ClientDao extends GenericDao<Client> {
    public Client getClient(String CNPCPF);
}
