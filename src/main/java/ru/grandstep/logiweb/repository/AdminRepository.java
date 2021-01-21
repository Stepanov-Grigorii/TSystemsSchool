package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.Admin;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class AdminRepository {
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public Admin saveOrUpdate(Admin admin){
        return entityManager.merge(admin);
    }
}
