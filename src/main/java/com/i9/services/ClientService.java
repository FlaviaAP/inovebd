package com.i9.services;

import com.i9.models.Client;

import java.util.List;

public interface ClientService {

    List<Client> getClients();

    Client getClient(String CNPJCPF);
}
