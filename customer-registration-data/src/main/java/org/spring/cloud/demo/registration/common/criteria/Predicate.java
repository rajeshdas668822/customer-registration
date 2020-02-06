package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Arrays;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonTypeInfo(use=JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property="op", visible = true, defaultImpl = SimplePredicate.class)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CompoundPredicate.class, name = "AND"),
        @JsonSubTypes.Type(value = CompoundPredicate.class, name = "OR")
})
public abstract class Predicate implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum PredicateOperator {
        AND, OR, EQ, IN, NE, GE, GT, LE, LT, LIKE
    }

    protected PredicateOperator op;


    public static Predicate and(Predicate... predicates) {
        return new CompoundPredicate(Arrays.asList(predicates), PredicateOperator.AND);
    }

    public static Predicate or(Predicate... predicates) {
        return new CompoundPredicate(Arrays.asList(predicates), PredicateOperator.OR);
    }

    public static Predicate eq(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.EQ);
    }

    public static Predicate in(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.IN);
    }

    public static Predicate ne(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.NE);
    }

    public static Predicate ge(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.GE);
    }

    public static Predicate gt(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.GT);
    }

    public static Predicate le(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.LE);
    }

    public static Predicate lt(String path, Object value) {
        return new SimplePredicate(path, value, PredicateOperator.LT);
    }

    public static Predicate like(String path, String pattern) {
        return new SimplePredicate(path, pattern, PredicateOperator.LIKE);
    }
}
