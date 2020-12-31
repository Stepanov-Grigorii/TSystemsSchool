package ru.grandstep.logiweb.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Order getById(Integer id){
        Order order = entityManager.find(Order.class, id);
        if(order == null){
            throw new RuntimeException("order with id" + id + " not found");
        }
        return order;
    }

    public List<Order> getAll(){
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o").getResultList();
    }

    @Transactional
    public Order saveOrUpdate(Order order){
        return entityManager.merge(order);
    }
}
