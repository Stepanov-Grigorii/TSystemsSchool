package ru.grandstep.logiweb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.util.Assert;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.repository.DriverRepository;

import static org.mockito.MockitoAnnotations.initMocks;

class DriverServiceTest {
    @Mock
    private DriverRepository driverRepository;

    private DriverService driverService;

    @BeforeEach
    public void setUp(){
        initMocks(this);
        driverService  = new DriverService(driverRepository);
    }

    @Test
    void saveOrUpdate() throws NotFoundException {
        Driver driver = new Driver();

        driver.setName("Name");
        driver.setSurname("Surname");

        driverService.saveOrUpdate(driver);
        Assert.notNull(driver.getIdentityNumber(), "[Assertion failed] - this argument is required; it must not be null");
    }
}