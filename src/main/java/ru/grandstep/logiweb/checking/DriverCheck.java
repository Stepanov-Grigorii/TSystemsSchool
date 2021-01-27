package ru.grandstep.logiweb.checking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.repository.DistanceRepository;
import ru.grandstep.logiweb.repository.OrderRepository;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class DriverCheck {
    private final OrderRepository orderRepository;
    private final DistanceRepository distanceRepository;

    public boolean check(Driver driver, Order order){
        if(driver.getCurrentCity() != order.getWagon().getCurrentCity()){
            return false;
        }
//        if(orderRepository.getByWagon(driver.getWagon().getRegistryNumber()).getStatus() != Order.Status.COMPLETED){
//            return false;
//        }
        Integer hoursBetween = distanceRepository.getByCities(order.getActionDeparture().getWaypoint().getCity(),
                                                              order.getActionDestination().getWaypoint().getCity()).getHours();
        return driver.getHoursInCurrentMonth() + hoursBetween <= Driver.MAX_HOURS;
    }

    public Map<String, List<String>> test(){
        return Map.of("surname", List.of("Test Message", "Error Message"));
    }
}
