package ru.reeson2003.applianceControl.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.reeson2003.applianceConntrol.service.api.ApplianceList;
import ru.reeson2003.applianceConntrol.service.api.ApplianceService;
import ru.reeson2003.applianceConntrol.service.inMemory.ApplianceListImpl;
import ru.reeson2003.applianceConntrol.service.inMemory.InMemoryApplianceService;
import ru.reeson2003.applianceControl.lamp.Lamp;
import ru.reeson2003.applianceControl.timer.Timer;

@SpringBootApplication
public class ApplianceControlApplication {

    @Bean
    public ApplianceList getApplianceList() {
        return new ApplianceListImpl(
                new Lamp(),
                new Timer()
        );
    }

    @Bean
    public ApplianceService getApplianceService() {
        return new InMemoryApplianceService(getApplianceList());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplianceControlApplication.class, args);
    }
}
