package application.core.beans.mail;

import application.core.api.manager.MailManager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Date;

@ManagedBean(name = "mailBean")
@RequestScoped
public class MailBean implements Serializable {

    //inject mailInfo
    @ManagedProperty(value = "#{mailInfoBean}")
    MailInfoBean mailInfoBean;

    //employeeManager for operations
    @EJB(beanInterface = MailManager.class)
    MailManager mailManager;


    public MailBean() {

    }

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

    public void sendMail() {
        Date data = new Date();


        String text = mailInfoBean.getText();
        mailManager.sendMail(text);
        System.out.println(text);

    }


}
