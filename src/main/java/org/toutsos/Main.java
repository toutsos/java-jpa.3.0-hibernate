package org.toutsos;

import jakarta.persistence.*;
import org.toutsos.entities.*;

import java.time.*;

public class Main {
    public static void main(String[] args) {

        //EntityManager -> manages jpa context (entities)
        //this context is DIFFERENT from spring context

        //EntityManagerFactory
        EntityManagerFactory entityManagerFactory =
                Persistence.createEntityManagerFactory("my-persistence-unit");

        EntityManager em = entityManagerFactory.createEntityManager();

        Product p = new Product();
        p.setId(100);
        p.setName("Beer");
        p.setPrice(10.4);
        p.setExpirationDate(LocalDate.now());

        Product p1 = new Product();
        p1.setId(101);
        p1.setName("Vodka");
        p1.setPrice(25.9);
        p1.setExpirationDate(LocalDate.now());

        try {
            em.getTransaction().begin();
            em.persist(p); //adding the instance in the jpa context
            em.persist(p1); //adding the instance in the jpa context
            em.getTransaction().commit();
        }catch (Exception ex){
            em.getTransaction().rollback();
        }finally {
            em.close();
        }


    }
}