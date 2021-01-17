package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Wagon;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
        return (List<Wagon>) entityManager.createQuery("SELECT w FROM Wagon w").getResultList();
    }

    public Wagon getByRegistryNumber(String number){
        return (Wagon) entityManager.createQuery("SELECT w FROM Wagon w WHERE w.registryNumber = :number")
                            .setParameter("number", number).getSingleResult();
    }

    @Transactional
    public Wagon saveOrUpdate(Wagon wagon){
        return entityManager.merge(wagon);
    }

    @Transactional
    public void delete(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Wagon w WHERE w.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}