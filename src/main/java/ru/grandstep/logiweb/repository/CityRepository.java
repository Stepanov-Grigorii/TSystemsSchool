package ru.grandstep.logiweb.repository;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import ru.grandstep.logiweb.model.City;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CityRepository {
    private final SessionFactory sessionFactory;

    public City getById(Integer id){
        try (Session session = sessionFactory.openSession()){
            return session.get(City.class, id);
        }
    }

    public List<City> getAll(){
        try(Session session = sessionFactory.openSession()){
            return session.createQuery("FROM City", City.class).list();
        }
    }

    public City saveOrUpdate(City city){
        try (Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(city);
            transaction.commit();
            return city;
        }
    }
}
