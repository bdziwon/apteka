package application.core.ejb.manager;


import application.core.api.dao.ClientDAO;
import application.core.api.exception.ClientNotFoundException;
import application.core.api.manager.ClientManager;
import application.core.model.Client;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless

public class DefaultClientManager implements ClientManager {
    @EJB
    private ClientDAO clientDAO;

    @Override
    public void removeClient(Client client) {
        clientDAO.removeClient(client);
    }

    @Override
    public void persistClient(Client client) {
        clientDAO.persistClient(client);
    }

    @Override
    public Client mergeClient(Client client) {
        return clientDAO.mergeClient(client);
    }

    @Override
    public Client findClient(Long id) throws ClientNotFoundException {
        Client client = clientDAO.findClient(id);
        if (client == null)
            throw new ClientNotFoundException();
        return client;
    }

    @Override
    public List<Client> findallClients() {
        return clientDAO.findallClients();
    }

}