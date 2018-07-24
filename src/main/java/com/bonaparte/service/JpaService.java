package com.bonaparte.service;

import com.bonaparte.entity.Charge;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by yangmingquan on 2018/7/24.
 * 原生jdbc的支持
 *    EntityManagerFactory
 *    EntityManager
 *    EntityTransaction
 */
@Service
public class JpaService {

    public void jpaCheck(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ponaparte");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        Charge charge = new Charge(1, 2.37);
        entityManager.persist(charge);
        entityTransaction.commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
