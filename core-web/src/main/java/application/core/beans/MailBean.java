package application.core.beans;

import application.core.api.manager.MailManager;
import application.core.beans.CredentialsBean;
import application.core.beans.MailInfoBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "mailBean")
@RequestScoped
public class MailBean implements Serializable {

    //inject credentials
    @Inject
    MailInfoBean mailInfoBean;

    //employeeManager for operations
    @EJB(beanInterface = MailManager.class)
    MailManager mailManager;


    public MailInfoBean getMailInfoBean() {
        return mailInfoBean;
    }

    public void setMailInfoBean(MailInfoBean mailInfoBean) {
        this.mailInfoBean = mailInfoBean;
    }

    public MailManager getMailManager() {
        return mailManager;
    }

    public void setMailManager(MailManager mailManager) {
        this.mailManager = mailManager;
    }

    public MailBean() {

    }

    public void send() {
        String  recipents    =   mailInfoBean.getRecipents();
        String  subject    =   mailInfoBean.getSubject();
        String  text       = mailInfoBean.getText();
        mailManager.sendMail(recipents, subject,text);
        System.out.println(recipents);
        System.out.println(subject);
        System.out.println(text);

    }


}
