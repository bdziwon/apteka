package application.core.api.manager;
import application.core.api.exception.ClientNotFoundException;
import application.core.model.Client;

import java.util.List;



import javax.ejb.Remote;

@Remote
public interface ClientManager {

    List<Client> findallClients();

    Client findClient(Long id) throws ClientNotFoundException;

    Client mergeClient(Client client);

    void persistClient(Client client);

    void removeClient(Client client);
}
