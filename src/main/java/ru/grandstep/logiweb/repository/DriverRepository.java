package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Driver;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
        List<Driver> drivers = (List<Driver>) entityManager.createQuery("SELECT d FROM Driver d", Driver.class).getResultList();
        return drivers;
    }

    @Transactional
    public Driver saveOrUpdate(Driver driver){
        return entityManager.merge(driver);
    }
}