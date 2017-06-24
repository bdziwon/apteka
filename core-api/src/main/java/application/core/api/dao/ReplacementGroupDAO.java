package application.core.api.dao;


import application.core.model.ReplacementGroup;

import java.util.List;

import javax.ejb.Local;
@Local

public interface ReplacementGroupDAO {
    void removeReplacementGroup(ReplacementGroup replacementGroup);

    void persistReplacementGroup(ReplacementGroup replacementGroup);


    ReplacementGroup mergeReplacementGroup(ReplacementGroup replacementGroup);

    ReplacementGroup findReplacementGroup(Long id);

    List<ReplacementGroup> findallReplacementGroups();

    ReplacementGroup findReplacementByName(String name);
}
