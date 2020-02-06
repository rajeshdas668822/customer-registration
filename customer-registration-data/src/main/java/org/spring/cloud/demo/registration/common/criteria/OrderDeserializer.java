package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OrderDeserializer extends JsonDeserializer<Order> {

    @Override
    public Order deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String name;
        boolean value;

        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() { };
        Map<String, Object> map = jp.readValueAs(typeRef);
        if (map.size() == 1) {
            Map.Entry<String, Object> entry = map.entrySet().iterator().next();
            name = entry.getKey();
            value = entry.getValue().toString().equalsIgnoreCase("asc");
        } else {
            name = (String) map.get("name");
            value = (boolean) map.get("asc");
        }
        return new Order(name, value);
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}