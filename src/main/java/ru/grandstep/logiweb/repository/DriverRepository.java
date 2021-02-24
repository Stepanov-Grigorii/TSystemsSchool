package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Driver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DriverRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Driver getById(Integer id) throws NotFoundException {
        Driver driver = entityManager.find(Driver.class, id);
        if (driver == null) {
            throw new NotFoundException("driver", id);
        }
        return driver;
    }

    public List<Driver> getAll() {
        return (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d").getResultList();
    }

    public List<String> getIdentityNumbers() {
        return (List<String>) entityManager.createQuery("SELECT d.identityNumber FROM Driver d").getResultList();
    }

    public List<Driver> getAllFreeDrivers() {
        return (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d WHERE d.wagon is null").getResultList();
    }

    public List<Driver> getAllDriversInWagon(Integer id) {
        return (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d WHERE d.wagon is not null AND d.wagon.id = :id")
                .setParameter("id", id).getResultList();
    }

    public List<Driver> getAllDriversByOrder(Integer id){
        return (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d, Order o WHERE d.wagon is not null AND o.id = :id AND d.wagon.id = o.wagon.id")
                .setParameter("id", id).getResultList();
    }

    @Transactional
    public Driver saveOrUpdate(Driver driver) {
        return entityManager.merge(driver);
    }

    @Transactional
    public void delete(Integer id) {
        Query query = entityManager.createQuery("DELETE FROM Driver d WHERE d.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}