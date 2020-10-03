package jpabook.jpashop;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        // DB 커넥션 단위 마다 이 entityManager 를 생성해 줘야 함
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try{

            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("롤백 작동");
            entityTransaction.rollback();
        } finally {
            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
