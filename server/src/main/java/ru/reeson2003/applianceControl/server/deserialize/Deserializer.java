package ru.reeson2003.applianceControl.server.deserialize;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import ru.reeson2003.applianceControl.api.Action;

import java.io.IOException;
import java.util.Arrays;

/**
 * @author Pavel Gavrilov
 */
public class Deserializer extends StdDeserializer<Action> {

    protected Deserializer(Class<?> vc) {
        super(vc);
    }

    protected Deserializer() {
        this(null);
    }

    @Override
    public Action deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = jp.getCodec().readTree(jp);
        String name = node.get("actionName").asText();
        ObjectMapper mapper = new ObjectMapper();
        DeserializableParameter[] deserializableParameters =
                mapper.readValue(node.get("parameters").traverse(), DeserializableParameter[].class);
        Action result = new DeserializableAction(name);
        Arrays.stream(deserializableParameters).forEach(p -> result.setParameter(p.getName(), p.getValue()));
        return result;
    }
}
