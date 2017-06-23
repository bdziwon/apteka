package application.core.beans.client;


import application.core.annotations.LoggedIn;
import application.core.api.exception.ClientNotFoundException;
import application.core.api.manager.ClientManager;
import application.core.api.manager.MedicineManager;
import application.core.beans.utility.MessageBean;
import application.core.model.Employee;
import application.core.model.Client;
import application.core.model.Recipe;

import javax.ejb.EJB;
import javax.ejb.SessionBean;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "clientBean")
@RequestScoped
public class ClientBean implements Serializable{
    @EJB(beanInterface = ClientManager.class)
    private ClientManager clientManager;


    @ManagedProperty(value="#{clientInformationBean}")
    ClientInformationBean clientInformationBean;

    @ManagedProperty(value = "#{messageBean}")
    MessageBean messageBean;

    private List<Client> filtredClients;

    public ClientBean(){

    }
    public void updateInformationBean() {
        Client client = null;

        try {
            client = clientManager
                    .findClient(clientInformationBean.getId());
        } catch (ClientNotFoundException e  ) {
            //do not update any form fields, replacementGroup with given name not found
            clientInformationBean.setId(null);
            System.out.println("updateInformationBean:ReplacementGroupNotFoundException");
            return;
        }

        //update fields with selected replacementGroup.
        System.out.println("updateInformationBean: updating fields");
        System.out.println("Id from database = " + client.getId());
        clientInformationBean.setId(client.getId());
        clientInformationBean.setName(client.getFirstName());
        clientInformationBean.setLastName(client.getLastName());
        clientInformationBean.setPhoneNumber(client.getPhoneNumber());
        clientInformationBean.setPesel(client.getPesel());


    }

    public void addClient() {
        Client client = new Client();
        client.setId(clientInformationBean.getId());
        System.out.println("Inserting client with id = " +client.getId());
        client.setFirstName(clientInformationBean.getName());
        client.setLastName(clientInformationBean.getLastName());
        client.setPhoneNumber(clientInformationBean.getPhoneNumber());
        client.setPesel(clientInformationBean.getPesel());

        client = clientManager.mergeClient(client);
        clientInformationBean.setId(client.getId());
        messageBean.addMessage("ReplacementGroup updated successfully, to work with other user change 'name' field");

    }

    public List<Client> getFiltredClients() {
        return filtredClients;
    }

    public void setFilteredReplacementGroups(List<Client> filtredClients) {
        this.filtredClients = filtredClients;
    }

    public void removeClient(Client client) {
        clientManager.removeClient(client);

    }


    public ClientManager getClientManager() {
        return clientManager;
    }

    public void setClientManager(ClientManager clientManager) {
        this.clientManager = clientManager;
    }

    public ClientInformationBean getClientInformationBean() {
        return clientInformationBean;
    }

    public void setClientInformationBean(ClientInformationBean clientInformationBean) {
        this.clientInformationBean = clientInformationBean;
    }

    public List<Client> getClients() {
        return clientManager.findallClients();
    }

    public void setFiltredClients(List<Client> filtredClients) {
        this.filtredClients = filtredClients;
    }

    public MessageBean getMessageBean() {
        return messageBean;
    }

    public void setMessageBean(MessageBean messageBean) {
        this.messageBean = messageBean;
    }


}



