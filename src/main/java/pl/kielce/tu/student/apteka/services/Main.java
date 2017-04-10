package pl.kielce.tu.student.apteka.services;

import pl.kielce.tu.student.apteka.model.Client;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.*;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bartek on 2017-04-09.
 */

public class Main {

    public static void main(String[] args) {
        EntityManager em = Persistence
                .createEntityManagerFactory("apteka")
                .createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();
    }

}
