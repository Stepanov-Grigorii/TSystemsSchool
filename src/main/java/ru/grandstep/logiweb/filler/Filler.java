package ru.grandstep.logiweb.filler;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final ActionRepository actionRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    @PostConstruct
    private void fill(){
        Faker faker = new Faker();

        Admin admin = new Admin();

        admin.setLogin("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        adminRepository.saveOrUpdate(admin);

        City cityS = new City();
        City cityM = new City();
        City cityNV = new City();

        cityS.setName("Saint-Petersburg");
        cityM.setName("Moscow");
        cityNV.setName("Nizhny Novgorod");

        cityS.setLatitude(59.57);
        cityS.setLongitude(30.19);

        cityM.setLatitude(30.19);
        cityM.setLongitude(37.36);

        List.of(cityS, cityM, cityNV).forEach(cityRepository::saveOrUpdate);

        Distance distance = new Distance();

        distance.setDistance(new BigDecimal("705.6"));
        distance.setFirst(cityRepository.getById(1));
        distance.setSecond(cityRepository.getById(2));
        distance.setHours(8);

        distanceRepository.saveOrUpdate(distance);

        distance.setDistance(new BigDecimal("1600"));
        distance.setFirst(cityRepository.getById(1));
        distance.setSecond(cityRepository.getById(3));
        distance.setHours(16);

        distanceRepository.saveOrUpdate(distance);

        distance.setDistance(new BigDecimal("600"));
        distance.setFirst(cityRepository.getById(2));
        distance.setSecond(cityRepository.getById(3));
        distance.setHours(6);

        distanceRepository.saveOrUpdate(distance);

        Cargo cargo = new Cargo();

        cargo.setNumber("A1S2D3");
        cargo.setName("Flour");
        cargo.setWeight(BigDecimal.valueOf(3.5));
        cargo.setStatus(Cargo.Status.PREPARED);

        Cargo cargo1 = new Cargo();

        cargo1.setNumber("A1S2D4");
        cargo1.setName("Wood");
        cargo1.setWeight(BigDecimal.valueOf(2));
        cargo1.setStatus(Cargo.Status.PREPARED);

        List.of(cargo, cargo1).forEach(cargoRepository::saveOrUpdate);

        Waypoint waypointS = new Waypoint();
        Waypoint waypointM = new Waypoint();
        Waypoint waypointNV = new Waypoint();

        waypointS.setName("Spb N1");
        waypointM.setName("Msk N1");
        waypointNV.setName("N-NV N1");

        waypointS.setAddress("Kad 117 km");
        waypointM.setAddress("Kaluzhskoe highway 21 km");
        waypointNV.setAddress("kakoito adress");

        waypointS.setCity(cityRepository.getById(1));
        waypointM.setCity(cityRepository.getById(2));
        waypointNV.setCity(cityRepository.getById(3));

        List.of(waypointS, waypointM, waypointNV).forEach(waypointRepository::saveOrUpdate);

        Action action1 = new Action();
        Action action2 = new Action();
        Action action3 = new Action();

        action1.setType(Action.Type.LOADING);
        action2.setType(Action.Type.UNLOADING);
        action3.setType(Action.Type.LOADING);

        cargo = cargoRepository.getById(1);
        action1.setCargo(cargo);
        action2.setCargo(cargo);

        cargo = cargoRepository.getById(2);
        action3.setCargo(cargo);

        action1.setWaypoint(waypointRepository.getById(1));
        action2.setWaypoint(waypointRepository.getById(2));
        action3.setWaypoint(waypointRepository.getById(1));

        List.of(action1, action2, action3).forEach(actionRepository::saveOrUpdate);

        Order order = new Order();

        order.setNumber("1A");
        order.setStatus(Order.Status.WAITING);
        order.setActionDeparture(actionRepository.getById(1));
        order.setActionDestination(actionRepository.getById(2));

        orderRepository.saveOrUpdate(order);

        for(int i = 0; i < 20; i++){
            Driver driver = new Driver();
            driver.setPassword(passwordEncoder.encode("qwerty"));
            driver.setLogin("driver" + (i + 1));
            driver.setName(DriverNames.getFirstName());
            driver.setSurname(DriverNames.getLastName());
            driver.setIdentityNumber(DriverID.getUserId(driver.getName(), driver.getSurname(), driverRepository.getIdentityNumbers()));
            driver.setEmail(faker.internet().emailAddress());
            driver.setStatus(Driver.Status.REST);
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
        volvo.setStatus(Wagon.Status.WORKED);
        volvo.setCurrentCity(cityRepository.getById(1));

        mercedes.setCapacity(10);
        mercedes.setRegistryNumber("M0002ER");
        mercedes.setDriverNumber(4);
        mercedes.setBrand("Mercedes");
        mercedes.setStatus(Wagon.Status.BROKEN);
        mercedes.setCurrentCity(cityRepository.getById(2));

        renault.setCapacity(5);
        renault.setRegistryNumber("R0003EN");
        renault.setDriverNumber(4);
        renault.setBrand("Renault");
        renault.setStatus(Wagon.Status.WORKED);
        renault.setCurrentCity(cityRepository.getById(1));

        mack.setCapacity(5);
        mack.setRegistryNumber("M0004CK");
        mack.setDriverNumber(4);
        mack.setBrand("Mack");
        mack.setStatus(Wagon.Status.WORKED);
        mack.setCurrentCity(cityRepository.getById(1));

        harleyDavidson.setCapacity(20);
        harleyDavidson.setRegistryNumber("H0005AD");
        harleyDavidson.setDriverNumber(4);
        harleyDavidson.setBrand("Harley Davidson");
        harleyDavidson.setStatus(Wagon.Status.WORKED);
        harleyDavidson.setCurrentCity(cityRepository.getById(2));

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

        order = orderRepository.getById(1);
        wagon = wagonRepository.getById(1);
        order.setWagon(wagon);

        orderRepository.saveOrUpdate(order);
    }
}