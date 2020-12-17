package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Cargo;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CargoRepository {
    private final SessionFactory sessionFactory;

    public Cargo getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Cargo.class, id);
        }
    }

    public List<Cargo> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Cargo", Cargo.class).list();
        }
    }

    public Cargo saveOrUpdate(Cargo cargo){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(cargo);
            transaction.commit();
            return cargo;
        }
    }
}
