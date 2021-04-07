package ru.grandstep.logiweb.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.dto.OrderIntegrationDTO;
import ru.grandstep.logiweb.dto.WagonIntegrationDTO;
import ru.grandstep.logiweb.mapper.OrderIntegrationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.grandstep.logiweb.mapper.WagonMapper;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.model.Wagon;
import ru.grandstep.logiweb.repository.OrderRepository;
import ru.grandstep.logiweb.service.DriverService;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivemqProducer {

    private final JmsTemplate jmsTemplate;
    private final OrderIntegrationMapper orderIntegrationMapper;
    private final WagonMapper wagonMapper;
    private final DriverService driverService;
    private final OrderRepository orderRepository;
    private final Logger activeMqLogger = LogManager.getLogger(ActivemqProducer.class);

    private final String ORDER_QUEUE_NAME = "new-orders";
    private final String WAGON_QUEUE_NAME = "new-wagons";


    public void sendOrder(Order order){
        OrderIntegrationDTO dto = orderIntegrationMapper.orderToOIM(order, driverService.getAllDriversByOrder(order.getId()));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            jmsTemplate.convertAndSend(ORDER_QUEUE_NAME, objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            activeMqLogger.error(e.getMessage());
        }
    }

    public void sendWagon(Wagon wagon){
        WagonIntegrationDTO dto = wagonMapper.getWagonInterDTO(wagon, new ArrayList<>());
        dto.setOccupied(orderRepository.getAllByWagonId(dto.getId()).isEmpty());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            jmsTemplate.convertAndSend(WAGON_QUEUE_NAME, objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            activeMqLogger.error(e.getMessage());
        }
    }
}
