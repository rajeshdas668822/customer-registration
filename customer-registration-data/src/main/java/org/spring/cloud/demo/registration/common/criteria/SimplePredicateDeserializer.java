package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class SimplePredicateDeserializer extends JsonDeserializer<SimplePredicate> {

    @Override
    public SimplePredicate deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String name = null;
        Object value = null;
        Predicate.PredicateOperator op = null;

        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<HashMap<String, Object>>() { };
        Map<String, Object> map = jp.readValueAs(typeRef);
        Set<Map.Entry<String, Object>> entrySet = map.entrySet();
        if (entrySet.size() == 2) {
            for (Map.Entry<String, Object> entry : entrySet) {
                if (entry.getKey().equals("op")) {
                    op = Predicate.PredicateOperator.valueOf(entry.getValue().toString());
                } else {
                    name = entry.getKey();
                    value = entry.getValue();
                }
            }
        } else {
            name = (String) map.get("name");
            value = map.get("value");
            op = Predicate.PredicateOperator.valueOf((String) map.get("op"));
        }
        return new SimplePredicate(name, value, op);
    }

    @Override
    public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
        return deserialize(p, ctxt);
    }
}