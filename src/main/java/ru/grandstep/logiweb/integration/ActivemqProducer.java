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
import ru.grandstep.logiweb.mapper.OrderIntegrationMapper;
import ru.grandstep.logiweb.model.Order;
import ru.grandstep.logiweb.service.DriverService;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivemqProducer {

    private final JmsTemplate jmsTemplate;
    private final OrderIntegrationMapper orderIntegrationMapper;
    private final DriverService driverService;

    private final String ORDER_QUEUE_NAME = "new-orders";


    public void sendOrder(Order order){
        OrderIntegrationDTO dto = orderIntegrationMapper.orderToOIM(order, driverService.getAllDriversByOrder(order.getId()));
        log.info("Send message to order queue = {}", dto);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            jmsTemplate.convertAndSend(ORDER_QUEUE_NAME, objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
