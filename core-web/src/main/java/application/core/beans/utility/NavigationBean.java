package application.core.beans.utility;

import javax.decorator.Decorator;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;


@ManagedBean(name = "navigationBean")
@SessionScoped
public class NavigationBean implements Serializable {

    public NavigationBean() {
    }

    /**
     * redirects to path from current position
     * @param path
     */

    public void redirectTo(String path) {

        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * redirect to path from root position
     * @param path
     */
    public void redirectFromTop(String path) {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        try {
            externalContext.redirect(externalContext.getRequestContextPath() + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
