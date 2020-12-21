package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Waypoint;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WaypointRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Waypoint getById(Integer id){
        Waypoint waypoint = entityManager.find(Waypoint.class, id);
        if(waypoint == null){
            throw new RuntimeException("Waypoint with id" + id + " not found");
        }
        return waypoint;
    }

    public List<Waypoint> getAll(){
        List<Waypoint> waypointList = (List<Waypoint>) entityManager.createQuery("SELECT w FROM Waypoint w", Waypoint.class).getResultList();
        return waypointList;
    }

    @Transactional
    public Waypoint saveOrUpdate(Waypoint waypoint){
        return entityManager.merge(waypoint);
    }
}