package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;

public class SimplePredicateSerializer extends JsonSerializer<SimplePredicate> {

    @Override
    public void serialize(SimplePredicate p, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeObjectField(p.getName(), p.getValue());
        jgen.writeObjectField("op", p.getOp());
    }

    @Override
    public void serializeWithType(SimplePredicate value, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForObject(value, gen);
        serialize(value, gen, provider);
        typeSer.writeTypeSuffixForObject(value, gen);
    }
}