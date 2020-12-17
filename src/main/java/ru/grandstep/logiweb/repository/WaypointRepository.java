package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Waypoint;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class WaypointRepository {
    private final SessionFactory sessionFactory;

    public Waypoint getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(Waypoint.class, id);
        }
    }

    public List<Waypoint> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM Waypoint", Waypoint.class).list();
        }
    }

    public Waypoint saveOrUpdate(Waypoint waypoint){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(waypoint);
            transaction.commit();
            return waypoint;
        }
    }
}
