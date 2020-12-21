package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Wagon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class WagonRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Wagon getById(Integer id){
        Wagon wagon = entityManager.find(Wagon.class, id);
        if(wagon == null){
            throw new RuntimeException("wagon with id" + id + " not found");
        }
        return wagon;
    }

    public List<Wagon> getAll(){
        List<Wagon> wagons = (List<Wagon>) entityManager.createQuery("SELECT w FROM Wagon w",  Wagon.class).getResultList();
        return wagons;
    }

    @Transactional
    public Wagon saveOrUpdate(Wagon wagon){
        return entityManager.merge(wagon);
    }
}