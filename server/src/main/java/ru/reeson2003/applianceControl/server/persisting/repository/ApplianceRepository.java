package ru.reeson2003.applianceControl.server.persisting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.reeson2003.applianceControl.server.persisting.entity.ApplianceEntity;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
@Repository
public interface ApplianceRepository extends JpaRepository<ApplianceEntity, Long> {
    Collection<ApplianceEntity> findByName();
}
