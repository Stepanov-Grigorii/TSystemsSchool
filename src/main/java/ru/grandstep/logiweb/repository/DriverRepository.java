package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Driver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DriverRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Driver getById(Integer id){
        Driver driver = entityManager.find(Driver.class, id);
        if(driver == null){
            throw new RuntimeException("driver with id = " + id + " not found");
        }
        return driver;
    }

    public List<Driver> getAll(){
        return (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d").getResultList();
    }

    public List<String> getIdentityNumbers(){
        return (List<String>) entityManager.createQuery("SELECT d.identityNumber FROM Driver d").getResultList();
    }

    @Transactional
    public Driver saveOrUpdate(Driver driver){
        return entityManager.merge(driver);
    }

    @Transactional
    public void delete(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Driver d WHERE d.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}