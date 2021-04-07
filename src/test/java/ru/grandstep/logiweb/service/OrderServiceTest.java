package ru.grandstep.logiweb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.Assert;
import ru.grandstep.logiweb.checking.DriverCheck;
import ru.grandstep.logiweb.checking.WagonCheck;
import ru.grandstep.logiweb.integration.ActivemqProducer;
import ru.grandstep.logiweb.repository.OrderRepository;

import static org.mockito.MockitoAnnotations.initMocks;

public class OrderServiceTest {
    @Mock
    private OrderRepository repository;
    @Mock
    private ActionService actionService;
    @Mock
    private WagonService wagonService;
    @Mock
    private DriverService driverService;
    @Mock
    private WaypointService waypointService;
    @Mock
    private WagonCheck wagonCheck;
    @Mock
    private DriverCheck driverCheck;
    @Mock
    private ActivemqProducer producer;

    private OrderService orderService;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        orderService = new OrderService(repository, actionService, wagonService, driverService,
                waypointService, wagonCheck, driverCheck, producer);
    }

    @Test
    void getOrderId() {
        Assert.notNull(orderService.getOrderId(), "[Assertion failed] - orderId argument is required; it must not be null");
    }
}
