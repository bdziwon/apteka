package application.core.ejb.manager;



import application.core.api.dao.ReplacementGroupDAO;
import application.core.api.exception.ReplacementGroupNotFoundException;
import application.core.api.manager.ReplacementGroupManager;
import application.core.model.ReplacementGroup;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
@Stateless
/**
 * Created by Hubert on 2017-05-28.
 */
public class DefaultReplacementGroupManager implements ReplacementGroupManager {
    @EJB
    private ReplacementGroupDAO replacementGroupDAO;

    @Override
    public void removeReplacementGroup(ReplacementGroup replacementGroup) {
        replacementGroupDAO.removeReplacementGroup(replacementGroup);
    }

    @Override
    public ReplacementGroup findReplacementByName(String name) {
        return replacementGroupDAO.findReplacementByName(name);
    }

    @Override
    public void persistReplacementGroup(ReplacementGroup replacementGroup) {
        replacementGroupDAO.persistReplacementGroup(replacementGroup);
    }

    @Override
    public ReplacementGroup mergeReplacementGroup(ReplacementGroup replacementGroup) {
        return replacementGroupDAO.mergeReplacementGroup(replacementGroup);
    }

    @Override
    public ReplacementGroup findReplacementGroup(Long id) throws ReplacementGroupNotFoundException {
        ReplacementGroup replacementGroup = replacementGroupDAO.findReplacementGroup(id);
        if (replacementGroup == null)
            throw new ReplacementGroupNotFoundException();
        return replacementGroup;
    }

    @Override
    public List<ReplacementGroup> findallReplacementGroups() {
        return replacementGroupDAO.findallReplacementGroups();
    }

}




