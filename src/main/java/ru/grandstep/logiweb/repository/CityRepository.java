package ru.grandstep.logiweb.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.grandstep.logiweb.model.City;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CityRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public City getById(Integer id){
        City city = entityManager.find(City.class, id);
        if(city == null){
            throw new RuntimeException("city with id" + id + " not found");
        }
        return city;
    }

    public List<City> getAll(){
        List<City> cities = (List<City>) entityManager.createQuery("SELECT c FROM City c", City.class).getResultList();
        return cities;
    }

    @Transactional
    public City saveOrUpdate(City city){
        return entityManager.merge(city);
    }
}