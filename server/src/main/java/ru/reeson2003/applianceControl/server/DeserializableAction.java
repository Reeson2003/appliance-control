package ru.reeson2003.applianceControl.server;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.reeson2003.applianceControl.api.Action;
import ru.reeson2003.applianceControl.api.Parameter;

import java.util.Collection;

/**
 * @author Pavel Gavrilov
 */
public class DeserializableAction extends Action {
    @JsonCreator
    public DeserializableAction(@JsonProperty("actionName") String name, @JsonProperty("Parameters") Collection<Parameter> parameters) {
        super(name);
        if (parameters != null)
            parameters.forEach(parameter -> setParameter(parameter.getName(), parameter.getValue()));
    }
}
