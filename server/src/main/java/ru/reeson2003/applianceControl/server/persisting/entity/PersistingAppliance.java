package ru.reeson2003.applianceControl.server.persisting.entity;

import ru.reeson2003.applianceConntrol.service.api.entity.ApplianceEntity;
import ru.reeson2003.applianceControl.api.Action;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
@Entity
@Table(name = "appliance")
public class PersistingAppliance implements ApplianceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "anme")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private StateEntity state;

    public PersistingAppliance(String name, StateEntity state) {
        this.name = name;
        this.state = state;
    }

    protected PersistingAppliance() {
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getIdentifier() {
        return null;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StateEntity getState() {
        return state;
    }

    @Override
    public Collection<Action> getActions() {
        return null;
    }

    public void setState(StateEntity state) {
        this.state = state;
    }
}
