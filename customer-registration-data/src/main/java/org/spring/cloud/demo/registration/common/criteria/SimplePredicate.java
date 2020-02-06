package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@Getter
@JsonSerialize(using = SimplePredicateSerializer.class)
@JsonDeserialize(using = SimplePredicateDeserializer.class)
public class SimplePredicate extends Predicate {

    @Setter
    private String name;
    @Setter
    private Object value;

    public SimplePredicate(String name, Object value, PredicateOperator op) {
        super(op);
        this.name = name;
        this.value = value;
    }
}
