package ru.reeson2003.applianceControl.server.persisting.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Pavel Gavrilov
 */
@Entity
@Table(name = "appliance")
public class ApplianceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String identifier;
    @Column
    private String stateName;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "appliance_parameter",
            joinColumns = @JoinColumn(name = "appliance_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private List<ParameterEntity> parameters;

    public ApplianceEntity(String identifier, String stateName, List<ParameterEntity> parameters) {
        this.identifier = identifier;
        this.stateName = stateName;
        this.parameters = parameters;
    }

    protected ApplianceEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public List<ParameterEntity> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterEntity> parameters) {
        this.parameters = parameters;
    }
}
