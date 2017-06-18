package application.core.api.dao;


import application.core.model.Client;

import java.util.List;

import javax.ejb.Local;


@Local
public interface ClientDAO {

    void removeClient(Client client);

    void persistClient(Client client);


    Client mergeClient(Client client);

    Client findClient(Long id);

    List<Client> findallClients();

}