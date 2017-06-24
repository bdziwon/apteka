package application.core.beans.utility;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "messageBean")
@ApplicationScoped
public class MessageBean {
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
