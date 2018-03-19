package ru.reeson2003.applianceControl.server.deserialize;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.reeson2003.applianceControl.api.Action;

/**
 * @author Pavel Gavrilov
 */
@JsonDeserialize(using = Deserializer.class)
public class DeserializableAction extends Action {
    public DeserializableAction(String name) {
        super(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append("{\n")
                .append("Name: ")
                .append(getName())
                .append("\n")
                .append("Parameters: [")
                .append("\n");
        getParameters().forEach(parameter -> {
            sb.append("\t")
                    .append(parameter.getName())
                    .append(": ")
                    .append(parameter.getValue())
                    .append("\n");
        });
        sb.append("\t]\n")
                .append("}");
        return sb.toString();
    }
}
