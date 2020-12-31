package ru.grandstep.logiweb.filler;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.*;
import ru.grandstep.logiweb.repository.*;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;


@Component
@RequiredArgsConstructor
public class Filler {
    private final DriverRepository driverRepository;
    private final WagonRepository wagonRepository;
    private final CityRepository cityRepository;
    private final DistanceRepository distanceRepository;
    private final OrderRepository orderRepository;
    private final CargoRepository cargoRepository;
    private final WaypointRepository waypointRepository;
    @PostConstruct
    private void fill(){
        Faker faker = new Faker();

        City cityS = new City();
        City cityM = new City();

        cityS.setName("Saint-Petersburg");
        cityM.setName("Moscow");

        cityS.setLatitude(59.57);
        cityS.setLongitude(30.19);

        cityM.setLatitude(30.19);
        cityM.setLongitude(37.36);

        List.of(cityS, cityM).forEach(cityRepository::saveOrUpdate);

        Distance distance = new Distance();

        distance.setDistance(new BigDecimal("705.6"));
        distance.setFirst(cityRepository.getById(1));
        distance.setSecond(cityRepository.getById(2));

        distanceRepository.saveOrUpdate(distance);

        Waypoint waypointS = new Waypoint();
        Waypoint waypointM = new Waypoint();

        waypointS.setType(Waypoint.Type.LOADING);
        waypointM.setType(Waypoint.Type.UNLOADING);

        List.of(waypointS, waypointM).forEach(waypointRepository::saveOrUpdate);

        Cargo cargo = new Cargo();

        cargo.setNumber("A1S2D3");
        cargo.setName("Flour");
        cargo.setStatus(Cargo.Status.PREPARED);

        cargoRepository.saveOrUpdate(cargo);

        Order order = new Order();

        order.setNumber("1A");
        order.setStatus(Order.Status.WAITING);
        order.setWaypointList(List.of(waypointRepository.getById(1), waypointRepository.getById(2)));

        orderRepository.saveOrUpdate(order);

        for(int i = 0; i < 20; i++){
            Driver driver = new Driver();
            driver.setName(DriverNames.getFirstName());
            driver.setSurname(DriverNames.getLastName());
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
            driver.setEmail(faker.internet().emailAddress());
            driverRepository.saveOrUpdate(driver);
        }

        Wagon volvo = new Wagon();
        Wagon mercedes = new Wagon();
        Wagon renault = new Wagon();
        Wagon mack = new Wagon();
        Wagon harleyDavidson = new Wagon();

        volvo.setCapacity(10);
        volvo.setRegistryNumber("V0001OL");
        volvo.setDriverNumber(4);
        volvo.setBrand("Volvo");

        mercedes.setCapacity(10);
        mercedes.setRegistryNumber("M0002ER");
        mercedes.setDriverNumber(4);
        mercedes.setBrand("Mercedes");

        renault.setCapacity(5);
        renault.setRegistryNumber("R0003EN");
        renault.setDriverNumber(4);
        renault.setBrand("Renault");

        mack.setCapacity(5);
        mack.setRegistryNumber("S0004CA");
        mack.setDriverNumber(4);
        mack.setBrand("Mack");

        harleyDavidson.setCapacity(20);
        harleyDavidson.setRegistryNumber("H005AD");
        harleyDavidson.setDriverNumber(4);
        harleyDavidson.setBrand("Harley Davidson");

        List.of(volvo, mercedes, renault, mack, harleyDavidson).forEach(wagonRepository::saveOrUpdate);

        Wagon wagon = wagonRepository.getById(1);
        for(int i = 1; i != 5; i++){
            Driver driver = driverRepository.getById(i);
            driver.setWagon(wagon);
            driverRepository.saveOrUpdate(driver);
        }
        wagon = wagonRepository.getById(2);
        for(int i = 5; i != 9; i++){
            Driver driver = driverRepository.getById(i);
            driver.setWagon(wagon);
            driverRepository.saveOrUpdate(driver);
        }
        wagon = wagonRepository.getById(3);
        for(int i = 9; i != 13; i++){
            Driver driver = driverRepository.getById(i);
            driver.setWagon(wagon);
            driverRepository.saveOrUpdate(driver);
        }
        wagon = wagonRepository.getById(4);
        for(int i = 13; i != 17; i++){
            Driver driver = driverRepository.getById(i);
            driver.setWagon(wagon);
            driverRepository.saveOrUpdate(driver);
        }
        wagon = wagonRepository.getById(5);
        for(int i = 17; i != 21; i++){
            Driver driver = driverRepository.getById(i);
            driver.setWagon(wagon);
            driverRepository.saveOrUpdate(driver);
        }




    }
}