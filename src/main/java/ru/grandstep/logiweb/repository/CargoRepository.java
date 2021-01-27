package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Cargo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CargoRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Cargo getById(Integer id) throws NotFoundException {
        Cargo cargo = entityManager.find(Cargo.class, id);
        if(cargo == null){
            throw new NotFoundException("cargo", id);
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

    @Transactional
    public void delete(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Cargo c WHERE c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}