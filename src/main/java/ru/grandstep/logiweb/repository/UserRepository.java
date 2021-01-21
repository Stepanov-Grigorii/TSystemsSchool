package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.Admin;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class UserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public User getByLogin(String login) {
        try {
            return (Driver) entityManager.createQuery("SELECT d FROM Driver d WHERE d.login = :login").setParameter("login", login).getSingleResult();
        } catch (NoResultException exception1) {
            try {
                return (Admin) entityManager.createQuery("SELECT a FROM Admin a WHERE a.login = :login").setParameter("login", login).getSingleResult();
            } catch (NoResultException exception2) {
                return null;
            }
        }
    }
}
