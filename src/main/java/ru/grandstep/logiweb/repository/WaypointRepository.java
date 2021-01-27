package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Waypoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class WaypointRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Waypoint getById(Integer id) throws NotFoundException {
        Waypoint waypoint = entityManager.find(Waypoint.class, id);
        if(waypoint == null){
            throw new NotFoundException("Waypoint", id);
        }
        return waypoint;
    }

    public Waypoint getByName(String name){
        return (Waypoint) entityManager.createQuery("SELECT w FROM Waypoint w WHERE w.name = :name")
                                       .setParameter("name", name).getSingleResult();
    }

    public List<Waypoint> getAll(){
        return (List<Waypoint>) entityManager.createQuery("SELECT w FROM Waypoint w").getResultList();
    }

    @Transactional
    public Waypoint saveOrUpdate(Waypoint waypoint){
        return entityManager.merge(waypoint);
    }
}