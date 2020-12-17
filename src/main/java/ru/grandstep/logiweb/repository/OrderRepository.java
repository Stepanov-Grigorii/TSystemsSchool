package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Order;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderRepository {
    private final SessionFactory sessionFactory;

    public Order getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Order.class, id);
        }
    }

    public List<Order> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Order", Order.class).list();
        }
    }

    public Order saveOrUpdate(Order order){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(order);
            transaction.commit();
            return order;
        }
    }
}
