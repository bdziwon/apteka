package application.core.ejb.dao;

import application.core.api.dao.ClientDAO;
import application.core.model.Client;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DefaultClientDAO implements ClientDAO {
    @PersistenceContext
    public EntityManager entityManager;

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
        entityManager.remove(entityManager.merge(client));
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}

