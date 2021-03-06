package application.core.api.manager;

import application.core.api.exception.ReplacementGroupNotFoundException;
import application.core.model.ReplacementGroup;

import java.util.List;



import javax.ejb.Remote;

@Remote
public interface ReplacementGroupManager {
    List<ReplacementGroup> findallReplacementGroups();

    ReplacementGroup findReplacementGroup(Long id) throws ReplacementGroupNotFoundException;

    ReplacementGroup mergeReplacementGroup(ReplacementGroup replacementGroup);

    void persistReplacementGroup(ReplacementGroup replacementGroup);

    void removeReplacementGroup(ReplacementGroup replacementGroup);

    ReplacementGroup findReplacementByName(String s);
}
