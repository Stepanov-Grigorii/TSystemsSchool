package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class OrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Order getById(Integer id) throws NotFoundException {
        Order order = entityManager.find(Order.class, id);
        if (order == null) {
            throw new NotFoundException("order", id);
        }
        return order;
    }

    public List<Order> getAll() {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o").getResultList();
    }

    public Order getByWagon(String wagonRegNumber) {
        return (Order) entityManager.createQuery("SELECT o FROM Order o WHERE o.wagon.registryNumber =:wagonRegNumber")
                .setParameter("wagonRegNumber", wagonRegNumber).getSingleResult();
    }

    public List<Order> getByDriverId(Integer driverId) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o, Driver d WHERE o.wagon.id = d.wagon.id AND d.id = :driverId")
                .setParameter("driverId", driverId).getResultList();
    }

    public List<Order> getAllByWagonId(Integer wagonId) {
        return (List<Order>) entityManager.createQuery("SELECT o FROM Order o WHERE o.wagon.id = :wagonId")
                .setParameter("wagonId", wagonId).getResultList();
    }

    public Order getByNumber(String number) {
        return (Order) entityManager.createQuery("SELECT o FROM Order o WHERE o.number = :number")
                .setParameter("number", number).getSingleResult();
    }

    public List<String> getOrderIds(){
        return (List<String>) entityManager.createQuery("SELECT o.number FROM Order o").getResultList();
    }

    @Transactional
    public Order saveOrUpdate(Order order) {
        return entityManager.merge(order);
    }

    @Transactional
    public void delete(Integer id) {
        Query query = entityManager.createQuery("DELETE FROM Order o WHERE o.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
