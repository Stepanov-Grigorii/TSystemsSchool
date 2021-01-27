package ru.grandstep.logiweb.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.grandstep.logiweb.exception.NotFoundException;
import ru.grandstep.logiweb.model.Driver;
import ru.grandstep.logiweb.service.DriverService;

@Component
@RequiredArgsConstructor
public class DriverScheduler {
    private final DriverService driverService;

    @Scheduled(cron="0 0 0 1 1/1 *")
    void scheduledMonthHoursZeroing() throws NotFoundException {
        for (Driver driver : driverService.getAll()) {
            driver.setHoursInCurrentMonth(0);
            driverService.saveOrUpdate(driver);
        }
    }
}
