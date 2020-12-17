package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Driver;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DriverRepository {
    private final SessionFactory sessionFactory;

    public Driver getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Driver.class, id);
        }
    }

    public List<Driver> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Driver", Driver.class).list();
        }
    }

    public Driver saveOrUpdate(Driver driver){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(driver);
            transaction.commit();
            return driver;
        }
    }
}