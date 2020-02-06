package org.spring.cloud.demo.registration.common.criteria;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;

import static org.spring.cloud.demo.registration.common.CommonConstatnt.GLOBAL_UNIQUE_KEY_SEPARATOR;
import static org.spring.cloud.demo.registration.common.criteria.Predicate.PredicateOperator.AND;
import static org.spring.cloud.demo.registration.common.criteria.Predicate.PredicateOperator.OR;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Criteria implements Serializable {


    private static final long serialVersionUID = 1L;

    private static final String KEY_SEPARATOR = Pattern.quote(GLOBAL_UNIQUE_KEY_SEPARATOR);

    private final List<Predicate> filters;

    private List<Order> orderBy;

    private Integer offset;

    private Integer size;

   // private Audit audit;

    @JsonIgnore
    private boolean distinct;

    private Set<String> includes;

    @JsonIgnore
    private Map<String, Object> valueMap;

    @JsonIgnore
    private Map<String, String> fieldMap;

    public Criteria() {
        this.filters = new ArrayList<>();
    }

    public static Criteria of() {
        return new Criteria();
    }

    public Criteria distinct() {
        this.distinct = true;
        return this;
    }

   /* public Criteria audit(Audit audit) {
        this.audit = audit;
        return this;
    }*/

    public Criteria includes(Set<String> includes) {
        this.includes = includes;
        return this;
    }

    public Criteria valueMap(Map<String, Object> map) {
        if (this.valueMap == null) {
            this.valueMap = map;
        }
        else if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                this.valueMap.putIfAbsent(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    public Criteria fieldMap(Map<String, String> map) {
        this.fieldMap = map;
        return this;
    }

    public Criteria and(Predicate... predicates) {
        filters.add(Predicate.and(predicates));
        return this;
    }

    public Criteria or(Predicate... predicates) {
        filters.add(Predicate.or(predicates));
        return this;
    }

    public Criteria eq(String name, Object value) {
        filters.add(Predicate.eq(name, value));
        return this;
    }

    public Criteria in(String name, Object value) {
        filters.add(Predicate.in(name, value));
        return this;
    }

    public Criteria ne(String name, Object value) {
        filters.add(Predicate.ne(name, value));
        return this;
    }

    public Criteria ge(String name, Object value) {
        filters.add(Predicate.ge(name, value));
        return this;
    }

    public Criteria gt(String name, Object value) {
        filters.add(Predicate.gt(name, value));
        return this;
    }

    public Criteria le(String name, Object value) {
        filters.add(Predicate.le(name, value));
        return this;
    }

    public Criteria lt(String name, Object value) {
        filters.add(Predicate.lt(name, value));
        return this;
    }

    public Criteria like(String name, String pattern) {
        filters.add(Predicate.like(name, pattern));
        return this;
    }

    public Criteria asc(String name) {
        if (this.orderBy == null) {
            this.orderBy = new ArrayList<>();
        }
        orderBy.add(new Order(name, true));
        return this;
    }

    public Criteria desc(String name) {
        if (this.orderBy == null) {
            this.orderBy = new ArrayList<>();
        }
        orderBy.add(new Order(name, false));
        return this;
    }

    /**
     * Check the criteria object is empty or not Check it has atleast one filter or order or fetch
     * size
     *
     * @return true/false
     */
    @JsonIgnore
    public boolean isEmpty() {
        return (filters.isEmpty()) &&
                CollectionUtils.isEmpty(orderBy) &&
                CollectionUtils.isEmpty(includes) &&
                //audit == null &&
                offset == null &&
                size == null;
    }

    /**
     * Add filters to existing criteria object
     *
     * @param filters
     */
    public void addFilters(List<Predicate> filters) {
        if (filters != null) {
            this.getFilters().addAll(filters);
        }
    }

    /**
     * Include param
     *
     * @param key
     */
    public Criteria include(String key) {
        if (includes == null) {
            this.includes = new HashSet<>();
        }
        this.includes.add(key);
        return this;
    }

    @JsonIgnore
    public Criteria includeDetail() {
        include("detail");
        return this;
    }

    @JsonIgnore
    public boolean isDetailIncluded() {
        if (includes == null) {
            return false;
        }
        return this.includes.contains("detail");
    }

    /**
     * Set offset value - from which record
     *
     * @param offset
     * @return Criteria
     */
    public Criteria offset(Integer offset) {
        this.offset = offset;
        return this;
    }

    /**
     * Set number of records to fetch
     *
     * @param size
     * @return Criteria
     */
    public Criteria size(Integer size) {
        this.size = size;
        return this;
    }

    /**
     * Apply any custom mapping properties. Used to change bean properties to JPA entity properties
     *
     */
    public void applyMapping() {
        if (this.getFilters() != null) {
            List<SimplePredicate> keyPredicates = new ArrayList<>(2);
            // Iterate all filter attributes to apply field and value mappings
            this.getFilters().forEach(predicate -> mapName(predicate, keyPredicates));
            if (!keyPredicates.isEmpty()) {
                this.getFilters().addAll(keyPredicates);
                keyPredicates.clear();
            }
        }
        if (this.getOrderBy() != null && MapUtils.isNotEmpty(fieldMap)) {
            Integer index = null;
            List<Order> list = this.getOrderBy();
            for (int i = 0; i < list.size(); i++) {
                Order o = list.get(i);
                String propName = fieldMap.get(o.getName());
                if (propName != null) {
                    if (o.getName().equalsIgnoreCase("key") && propName.contains(GLOBAL_UNIQUE_KEY_SEPARATOR)) {
                        index = i;
                    }
                    o.setName(propName);
                }
            }
            if (index != null) {
                Order item = this.getOrderBy().get(index);
                String[] split = item.getName().split(KEY_SEPARATOR);
                for (int i = 0; i < split.length; i++) {
                    String s = split[i];
                    if (i == 0) {
                        this.getOrderBy().set(index, new Order(s, item.isAsc()));
                    } else {
                        this.getOrderBy().add(index + i, new Order(s, item.isAsc()));
                    }
                }
            }
        }
    }

    private void mapName(Predicate predicate, final List<SimplePredicate> keyPredicates) {
        Predicate.PredicateOperator op = predicate.getOp();
        if (op == AND || op == OR) {
            ((CompoundPredicate) predicate).getPredicates().forEach(p -> mapName(p, keyPredicates));
            if (!keyPredicates.isEmpty()) {
                ((CompoundPredicate) predicate).getPredicates().addAll(keyPredicates);
                keyPredicates.clear();
            }
        } else {
            SimplePredicate sp = (SimplePredicate) predicate;
            // Check field map contains this attribute
            String propName = (fieldMap != null) ? fieldMap.get(sp.getName()) : null;
            if (propName != null) {
                // If its is a composite key
                if (sp.getName().equalsIgnoreCase("key") && propName.contains(GLOBAL_UNIQUE_KEY_SEPARATOR)) {
                    String value = (String) sp.getValue();
                    String[] keys = propName.split(KEY_SEPARATOR);
                    String[] values = value.split(KEY_SEPARATOR);
                    for (int i = 0; i < keys.length; i++) {
                        if (i == 0) {
                            sp.setName(keys[0]);
                            sp.setValue(values[0]);
                        }
                        else if (i < values.length) {
                            SimplePredicate newPredicate = new SimplePredicate(keys[i], values[i], sp.getOp());
                            keyPredicates.add(newPredicate);
                        }
                    }
                }
                // If its a single key
                else {
                    sp.setName(propName);
                }
            }
            // Replace value from given map
            if (sp.getValue() != null && valueMap != null) {
                if (sp.getValue() instanceof Collection) {
                    List list;
                    if (sp.getValue() instanceof Set) {
                        list = new ArrayList((Set)sp.getValue());
                        sp.setValue(list);
                    }
                    list = (List) sp.getValue();
                    for (int i = 0; i < list.size(); i++) {
                        Object o = valueMap.get(list.get(i));
                        if (o != null) {
                            list.set(i, o);
                        }
                    }
                }
                else {
                    Object o = valueMap.get(sp.getValue());
                    if (o != null) {
                        sp.setValue(o);
                    }
                }
            }
        }
    }

    @Override
    public Criteria clone() {
        Criteria c = new Criteria();
        c.filters.addAll(this.filters);
        c.orderBy = this.orderBy;
        c.offset = this.offset;
        c.size = this.size;
        //c.audit = this.audit;
        c.includes = this.includes;
        return c;
    }

    public Criteria merge(Criteria c) {
        if (c == null) {
            return this;
        }
        if (c.getFilters() != null) {
            this.filters.addAll(c.getFilters());
        }
        if (c.getOrderBy() != null) {
            if (this.orderBy != null) {
                this.orderBy.addAll(c.getOrderBy());
            }
            else {
                this.orderBy = c.getOrderBy();
            }
        }
       /* if (c.getAudit() != null) {
            this.audit = c.getAudit();
        }*/
        if (c.getIncludes() != null) {
            if (this.getIncludes() != null) {
                this.includes.addAll(c.getIncludes());
            }
            else {
                this.includes = c.getIncludes();
            }
        }
        return this;
    }
}

