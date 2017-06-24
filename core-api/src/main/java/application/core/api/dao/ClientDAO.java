package application.core.api.dao;


import application.core.model.Client;

import javax.ejb.Local;
import java.util.List;


@Local
public interface ClientDAO {

    void removeClient(Client client);

    void persistClient(Client client);


    Client mergeClient(Client client);

    Client findClient(Long id);

    List<Client> findallClients();

}