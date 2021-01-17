package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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

    public Order getByWagon(String wagonRegNumber){
        return (Order) entityManager.createQuery("SELECT o FROM Order o WHERE o.wagon.registryNumber =:wagonRegNumber")
                                    .setParameter("wagonRegNumber", wagonRegNumber).getSingleResult();
    }

    @Transactional
    public Order saveOrUpdate(Order order){
        return entityManager.merge(order);
    }

    @Transactional
    public void delete(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Order o WHERE o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
