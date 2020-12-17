package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Wagon;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WagonRepository {
    private final SessionFactory sessionFactory;

    public Wagon getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Wagon.class, id);
        }
    }

    public List<Wagon> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Wagon", Wagon.class).list();
        }
    }

    public Wagon saveOrUpdate(Wagon wagon){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(wagon);
            transaction.commit();
            return wagon;
        }
    }
}