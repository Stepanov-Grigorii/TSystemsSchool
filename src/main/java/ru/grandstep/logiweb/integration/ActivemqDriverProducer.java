package ru.grandstep.logiweb.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ru.grandstep.logiweb.dto.DriverIntegrationDTO;
import ru.grandstep.logiweb.mapper.DriverMapper;
import ru.grandstep.logiweb.model.Driver;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ActivemqDriverProducer {

    private final JmsTemplate jmsTemplate;
    private final DriverMapper driverMapper;
    private final Logger driverLogger = LogManager.getLogger(ActivemqDriverProducer.class);
    private final String DRIVER_QUEUE_NAME = "new-drivers";

    public void sendDriver(Driver driver){
        DriverIntegrationDTO dto = driverMapper.getDriverInterDTO(driver, new ArrayList<>());
        driverLogger.info("Send message to driver queue = {}", dto);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        try {
            jmsTemplate.convertAndSend(DRIVER_QUEUE_NAME, objectMapper.writeValueAsString(dto));
        } catch (JsonProcessingException e) {
            driverLogger.error(e.getMessage());
        }
    }
}
