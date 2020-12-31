package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Cargo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CargoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Cargo getById(Integer id){
        Cargo cargo = entityManager.find(Cargo.class, id);
        if(cargo == null){
            throw new RuntimeException("cargo with id" + id + " not found");
        }
        return cargo;
    }

    public List<Cargo> getAll(){
        return (List<Cargo>) entityManager.createQuery("SELECT c FROM Cargo c").getResultList();
    }

    @Transactional
    public Cargo saveOrUpdate(Cargo cargo){
        return entityManager.merge(cargo);
    }
}