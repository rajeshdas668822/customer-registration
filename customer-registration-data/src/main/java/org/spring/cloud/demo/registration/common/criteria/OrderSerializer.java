package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import java.io.IOException;

public class OrderSerializer extends JsonSerializer<Order> {

    @Override
    public void serialize(Order p, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField(p.getName(), (p.isAsc()) ? "asc" : "desc");
        jgen.writeEndObject();
    }

    @Override
    public void serializeWithType(Order p, JsonGenerator gen, SerializerProvider provider, TypeSerializer typeSer) throws IOException {
        typeSer.writeTypePrefixForObject(p, gen);
        serialize(p, gen, provider);
        typeSer.writeTypeSuffixForObject(p, gen);
    }
}