package jpabook.jpashop;

import jpabook.jpashop.domain.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.sql.Date;
import java.time.LocalDateTime;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hello");

        // DB 커넥션 단위 마다 이 entityManager 를 생성해 줘야 함
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        entityTransaction.begin();
        try{
            Member member = new Member();
            member.setName("폴라");
            member.setCity("서울");
            entityManager.persist(member);

            Delivery delivery = new Delivery();
            delivery.setStatus(DeliveryStatus.COMP);
            entityManager.persist(delivery);

            Order order = new Order();
            order.setStatus(OrderStatus.ORDER);
            order.setDelivery(delivery);
            order.setMember(member);
            entityManager.persist(order);

            Order order2 = new Order();
            order2.setStatus(OrderStatus.ORDER);
            order2.setDelivery(delivery);
            order2.setMember(member);
            entityManager.persist(order2);

            entityTransaction.commit();
        } catch (Exception e) {
            System.out.println("롤백 작동");
            entityTransaction.rollback();
        } finally {
            System.out.println("끝");
            entityManager.close();
        }
        entityManagerFactory.close();

    }
}
/*

drop table DELIVERY;
drop table MEMBER ;
drop table ORDERS ;

select * from DELIVERY;
select * from MEMBER ;
select * from ORDERS ;

 */