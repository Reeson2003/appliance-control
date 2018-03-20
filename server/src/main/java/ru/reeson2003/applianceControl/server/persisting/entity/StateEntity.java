package ru.reeson2003.applianceControl.server.persisting.entity;

import javax.persistence.*;
import java.util.List;

/**
 * @author Pavel Gavrilov
 */
@Entity
@Table(name = "state")
public class StateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "state_parameters",
            joinColumns = @JoinColumn(name = "state_id"),
            inverseJoinColumns = @JoinColumn(name = "parameter_id")
    )
    private List<ParameterEntity> parameters;

    public StateEntity(String name, List<ParameterEntity> parameters) {
        this.name = name;
        this.parameters = parameters;
    }

    public StateEntity() {
    }

    public Long getId() {
        return id;
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

    public List<ParameterEntity> getParameters() {
        return parameters;
    }

    public void setParameters(List<ParameterEntity> parameters) {
        this.parameters = parameters;
    }
}
