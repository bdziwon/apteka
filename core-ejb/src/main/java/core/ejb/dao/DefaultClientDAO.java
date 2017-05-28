package core.ejb.dao;
import core.api.dao.ClientDAO;
import core.model.Client;
import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
/**
 * Created by Hubert on 2017-05-28.
 */
@Stateless
public class DefaultClientDAO implements ClientDAO{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client findClient(Long id) {
        return entityManager.find(Client.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Client> findallClients() {
        return entityManager.createNamedQuery("Client.findAllOrdered").getResultList();
    }

    @Override
    public Client mergeClient(Client client) {
        return entityManager.merge(client);
    }

    @Override
    public void persistClient(Client client) {
        entityManager.persist(client);
    }

    @Override
    public void removeClient(Client client) {
        entityManager.remove(client);
    }

}

