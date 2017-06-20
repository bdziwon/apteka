package application.core.ejb.dao;

import application.core.api.dao.ReplacementGroupDAO;
import application.core.model.ReplacementGroup;

import java.util.List;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Created by Hubert on 2017-05-28.
 */
@Stateless
public class DefaultReplecementGruopDAO implements ReplacementGroupDAO {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public ReplacementGroup findReplacementGroup(Long id) {
        return entityManager.find(ReplacementGroup.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ReplacementGroup> findallReplacementGroups() {
        return entityManager.createNamedQuery("ReplacementGroup.findAllOrdered").getResultList();
    }

    @Override
    public ReplacementGroup findReplacementByName(String name) {
        try {
            return (ReplacementGroup) entityManager.createNamedQuery("ReplacementGroup.findByName")
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }

    @Override
    public ReplacementGroup mergeReplacementGroup(ReplacementGroup replacementGroup) {
        return entityManager.merge(replacementGroup);
    }

    @Override
    public void persistReplacementGroup(ReplacementGroup replacementGroup) {
        entityManager.persist(replacementGroup);
    }

    @Override
    public void removeReplacementGroup(ReplacementGroup replacementGroup) {
        entityManager.remove(entityManager.merge(replacementGroup));
    }

}
