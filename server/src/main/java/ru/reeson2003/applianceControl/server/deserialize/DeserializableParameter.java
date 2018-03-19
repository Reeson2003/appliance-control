package ru.reeson2003.applianceControl.server.deserialize;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.reeson2003.applianceControl.api.Parameter;

/**
 * @author Pavel Gavrilov
 */
public class DeserializableParameter extends Parameter {
    @JsonCreator
    public DeserializableParameter(@JsonProperty("name") String name, @JsonProperty("value") String value) {
        super(name, value);
    }
}
