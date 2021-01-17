package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.City;
import ru.grandstep.logiweb.model.Distance;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DistanceRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Distance getById(Integer id){
        Distance distance = entityManager.find(Distance.class, id);
        if(distance == null){
            throw new RuntimeException("distance with id" + id + " not found");
        }
        return distance;
    }

    public List<Distance> getAll(){
        return (List<Distance>) entityManager.createQuery("SELECT d FROM Distance d").getResultList();
    }

    public Distance getByCities(City first, City second){
        return (Distance) entityManager.createQuery("SELECT d FROM Distance d WHERE (d.first.id = :firstId AND d.second.id = :secondId) " +
                                                       "OR " +
                                                       "(d.first.id = :secondId AND d.second.id = :firstId)")
                                       .setParameter("firstId", first.getId())
                                       .setParameter("secondId", second.getId())
                                       .getSingleResult();
    }

    @Transactional
    public Distance saveOrUpdate(Distance distance){
        return entityManager.merge(distance);
    }
}