package ru.reeson2003.applianceControl.server.persisting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.reeson2003.applianceControl.server.persisting.entity.PersistingAppliance;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public interface ApplianceRepository extends JpaRepository<PersistingAppliance, Long> {
    Collection<PersistingAppliance> findByName();
}
