package ru.reeson2003.applianceControl.server.persisting.entity;

import javax.persistence.*;

/**
 * @author Pavel Gavrilov
 */
@Entity
@Table(name = "parameter")
public class ParameterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "key")
    private String key;
    @Column(name = "valie")
    private String value;

    public ParameterEntity(String key, String value) {
        this.key = key;
        this.value = value;
    }

    protected ParameterEntity() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
