package application.core.beans.replacement;

import application.core.api.manager.ReplacementGroupManager;
import application.core.model.ReplacementGroup;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Bartek on 20.06.2017.
 */

@RequestScoped
@ManagedBean(name = "replacementGroupBean")
public class ReplacementGroupBean implements Serializable {

    @EJB(beanInterface = ReplacementGroupManager.class)
    private ReplacementGroupManager replacementGroupManager;

    public List<ReplacementGroup> getList() {
        return replacementGroupManager.findallReplacementGroups();
    }


}
