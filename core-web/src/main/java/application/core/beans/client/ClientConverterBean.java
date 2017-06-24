package application.core.beans.client;

import application.core.api.exception.ClientNotFoundException;
import application.core.api.manager.ClientManager;
import application.core.model.Client;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "clientConverter")
@ManagedBean(name = "clientConverterBean")
public class ClientConverterBean implements Converter {

    @EJB(beanInterface = ClientManager.class)
    private ClientManager clientManager;

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        try {
            Client client
                    = clientManager.findClient(new Long(s));

            return client;
        } catch (ClientNotFoundException | NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o == null) {
            return null;
        }
        String result = String.valueOf(((Client) o).getId());
        return result;
    }

    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

}