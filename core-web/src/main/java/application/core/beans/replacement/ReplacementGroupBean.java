package application.core.beans.replacement;

import application.core.api.exception.ReplacementGroupNotFoundException;
import application.core.api.manager.ReplacementGroupManager;
import application.core.beans.utility.MessageBean;
import application.core.model.ReplacementGroup;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@ManagedBean(name = "replacementGroupBean")
public class ReplacementGroupBean implements Serializable {

    @EJB(beanInterface = ReplacementGroupManager.class)
    private ReplacementGroupManager replacementGroupManager;

    //inject medicine information for adding and editing purposes
    @ManagedProperty(value="#{replacementGroupInformationBean}")
    ReplacementGroupInformationBean replacementGroupInformationBean;

    @ManagedProperty(value="#{messageBean}")
    MessageBean messageBean;

    List<ReplacementGroup> filteredReplacementGroups;


    public ReplacementGroupBean() {
    }



    public void updateInformationBean() {
        ReplacementGroup replacementGroup = null;

        try {
            replacementGroup = replacementGroupManager
                    .findReplacementGroup(replacementGroupInformationBean.getId());
        } catch (ReplacementGroupNotFoundException e) {
            //do not update any form fields, replacementGroup with given name not found
            replacementGroupInformationBean.setId(null);
            System.out.println("updateInformationBean:ReplacementGroupNotFoundException");
            return;
        }

        //update fields with selected replacementGroup.
        System.out.println("updateInformationBean: updating fields");
        System.out.println("Id from database = " + replacementGroup.getId());
        replacementGroupInformationBean.setId(replacementGroup.getId());
        replacementGroupInformationBean.setName(replacementGroup.getName());
    }

    public void addReplacementGroup() {
        ReplacementGroup replacementGroup = new ReplacementGroup();
        replacementGroup.setId(replacementGroupInformationBean.getId());
        System.out.println("Inserting replacementGroup with id = " +replacementGroup.getId());
        replacementGroup.setName(replacementGroupInformationBean.getName());

        replacementGroup = replacementGroupManager.mergeReplacementGroup(replacementGroup);
        replacementGroupInformationBean.setId(replacementGroup.getId());
        messageBean.addMessage("ReplacementGroup updated successfully, to work with other user change 'name' field");

    }

    public List<ReplacementGroup> getFilteredReplacementGroups() {
        return filteredReplacementGroups;
    }

    public void setFilteredReplacementGroups(List<ReplacementGroup> filteredReplacementGroups) {
        this.filteredReplacementGroups = filteredReplacementGroups;
    }

    public void removeReplacementGroup(ReplacementGroup replacementGroup) {
        replacementGroupManager.removeReplacementGroup(replacementGroup);

    }


    public ReplacementGroupManager getReplacementGroupManager() {
        return replacementGroupManager;
    }

    public void setReplacementGroupManager(ReplacementGroupManager replacementGroupManager) {
        this.replacementGroupManager = replacementGroupManager;
    }

    public ReplacementGroupInformationBean getReplacementGroupInformationBean() {
        return replacementGroupInformationBean;
    }

    public void setReplacementGroupInformationBean(ReplacementGroupInformationBean replacementGroupInformationBean) {
        this.replacementGroupInformationBean = replacementGroupInformationBean;
    }

    public List<ReplacementGroup> getReplacementGroups() {
        return replacementGroupManager.findallReplacementGroups();
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }
}
