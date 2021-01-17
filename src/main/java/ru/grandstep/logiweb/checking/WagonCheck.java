package ru.grandstep.logiweb.checking;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.model.Cargo;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.OrderRepository;

@Component
@RequiredArgsConstructor
public class WagonCheck {
    private final OrderRepository orderRepository;

    public boolean check(Wagon wagon, Cargo cargo){
        if(wagon.getStatus() == Wagon.Status.BROKEN){
            return false;
        }
        if(orderRepository.getByWagon(wagon.getRegistryNumber()) != null){
            return false;
        }
        return wagon.getCapacity().doubleValue() >= cargo.getWeigh().doubleValue();
    }
}
