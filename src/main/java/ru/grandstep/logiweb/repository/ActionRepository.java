package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ActionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Action getById(Integer id){
        Action action = entityManager.find(Action.class, id);
        if(action == null){
            throw new RuntimeException("action with id = " + id + " not found");
        }
        return action;
    }

    public Action getByCargoNumber(String number){
        return (Action) entityManager.createQuery("SELECT a FROM Action a WHERE a.cargo.number = :number")
                                     .setParameter("number", number).getSingleResult();
    }

    public Action getByCargoId(Integer id){
        return (Action) entityManager.createQuery("SELECT a FROM Action a WHERE a.cargo.id = :id")
                                     .setParameter("id", id).getSingleResult();
    }

    @Transactional
    public Action saveOrUpdate(Action action){
        return entityManager.merge(action);
    }
}
