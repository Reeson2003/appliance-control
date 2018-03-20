package ru.reeson2003.applianceControl.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.reeson2003.applianceControl.service.api.ApplianceList;
import ru.reeson2003.applianceControl.service.api.ApplianceService;
import ru.reeson2003.applianceControl.service.inMemory.ApplianceListImpl;
import ru.reeson2003.applianceControl.lamp.Lamp;
import ru.reeson2003.applianceControl.server.persisting.PersistingApplianceService;
import ru.reeson2003.applianceControl.server.persisting.repository.ApplianceRepository;
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
    @Autowired
    public ApplianceService getApplianceService(ApplianceRepository repository) {
        return new PersistingApplianceService(repository, getApplianceList());
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplianceControlApplication.class, args);
    }
}
