package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Distance;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class DistanceRepository {
    private final SessionFactory sessionFactory;

    public Distance getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Distance.class, id);
        }
    }

    public List<Distance> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Distance", Distance.class).list();
        }
    }

    public Distance saveOrUpdate(Distance distance){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(distance);
            transaction.commit();
            return distance;
        }
    }
}
