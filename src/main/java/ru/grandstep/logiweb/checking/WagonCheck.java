package ru.grandstep.logiweb.checking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.Action;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.OrderRepository;
import ru.grandstep.logiweb.service.ActionService;
import ru.grandstep.logiweb.service.WagonService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WagonCheck {
    private final OrderRepository orderRepository;
    private final WagonService wagonService;
    private final ActionService actionService;

    public boolean check(Wagon wagon, Cargo cargo){
        if(wagon.getStatus() == Wagon.Status.BROKEN){
            return false;
        }
        Action action = actionService.getByCargoId(cargo.getId());
        if(!wagon.getCurrentCity().equals(action.getWaypoint().getCity())){
            return false;
        }
//        if(orderRepository.getByWagon(wagon.getRegistryNumber()) != null){
//            return false;
//        }
        return wagon.getCapacity().doubleValue() >= cargo.getWeight().doubleValue();
    }

    public List<Wagon> checkedWagonList(List<Wagon> wagons, Cargo cargo){

        List<Wagon> checkedWagons = wagonService.getAll();
        for (Wagon wagon : wagons) {
            if(!check(wagon, cargo)){
                checkedWagons.remove(wagon);
            }
        }

        return checkedWagons;
    }
}
