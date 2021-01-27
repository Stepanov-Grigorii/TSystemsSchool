package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Action;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Repository
public class ActionRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public Action getById(Integer id) throws NotFoundException {
        Action action = entityManager.find(Action.class, id);
        if(action == null){
            throw new NotFoundException("action", id);
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

    public Action getByCargoIdAndType(Integer id, Action.Type type){
        Action action = new Action();
        try {
            action = (Action) entityManager.createQuery("SELECT a FROM Action a WHERE a.cargo.id = :id AND a.type = :type")
                    .setParameter("id", id).setParameter("type", type).getSingleResult();
        }catch (NoResultException ignored){

        }
        return action;
    }

    @Transactional
    public Action saveOrUpdate(Action action){
        return entityManager.merge(action);
    }

    @Transactional
    public void delete(Integer id){
        Query query = entityManager.createQuery("DELETE FROM Action a WHERE a.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
