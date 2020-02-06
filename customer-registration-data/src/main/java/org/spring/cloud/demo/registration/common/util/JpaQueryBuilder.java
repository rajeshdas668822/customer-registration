package org.spring.cloud.demo.registration.common.util;
import org.apache.commons.collections4.CollectionUtils;
import org.spring.cloud.demo.registration.common.criteria.*;
import org.spring.cloud.demo.registration.common.criteria.Order;
import org.spring.cloud.demo.registration.common.criteria.Predicate;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.spring.cloud.demo.registration.common.CommonConstatnt.GLOBAL_UNIQUE_KEY_SEPARATOR;
import static org.spring.cloud.demo.registration.common.criteria.Predicate.PredicateOperator.AND;
import static org.spring.cloud.demo.registration.common.criteria.Predicate.PredicateOperator.OR;
import static org.spring.cloud.demo.registration.common.util.CriteriaHelper.convertValueToTargetType;
import static org.spring.cloud.demo.registration.common.util.JpaCriteriaHelper.getCompoundJoinedPath;

public class JpaQueryBuilder {

    private static <T> CriteriaQuery<T> buildQuery(CriteriaBuilder b, Class<T> clazz, Criteria c) {
        CriteriaQuery<T> q = b.createQuery(clazz);
        Root<T> root = q.from(clazz);
        q.select(root);
        return buildQuery(b, q, c);
    }

    private static <T> CriteriaQuery<T> buildQuery(CriteriaBuilder b, CriteriaQuery<T> q, Criteria c) {
        if (c == null) {
            return q;
        }
        if (!c.getFilters().isEmpty()) {
            javax.persistence.criteria.Predicate[] p;
            if (null != q.getRestriction()) {
                p = buildPredicates(b, q, c.getFilters(), true);
                p[0] = q.getRestriction();
            } else {
                p = buildPredicates(b, q, c.getFilters());
            }
            q.where(p);
        }
        if (CollectionUtils.isNotEmpty(c.getOrderBy())) {
            List<javax.persistence.criteria.Order> orderList = new ArrayList<>(q.getOrderList().size() + c.getOrderBy().size());
            orderList.addAll(q.getOrderList());
            for (Order o : c.getOrderBy()) {
                orderList.add(JpaCriteriaHelper.buildOrder(b, q, o));
            }
            q.orderBy(orderList);
        }
        if (c.isDistinct()) {
            q.distinct(true);
        }
        return q;
    }

    private static <T> CriteriaQuery<Long> buildCountQuery(CriteriaBuilder b, Class<T> clazz, Criteria c) {
        CriteriaQuery<Long> q = b.createQuery(Long.class);
        Root<T> root = q.from(clazz);
        q.select(b.count(root));
        if (c != null && !c.getFilters().isEmpty()) {
            q.where(buildPredicates(b, q, c.getFilters()));
        }
        return q;
    }

    public static <T> List<Tuple> queryByFields(EntityManager em, Class<T> clazz, Criteria criteria, Set<String> fields) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Tuple> query = builder.createTupleQuery();
        Root<T> root = query.from(clazz);
        Map<String, String> mappings = (criteria.getFieldMap() == null) ? Collections.emptyMap() : criteria.getFieldMap();
        String key = mappings.get("key");
        String[] split = null;
        if (key != null && key.contains(GLOBAL_UNIQUE_KEY_SEPARATOR)) {
            split = key.split(Pattern.quote(GLOBAL_UNIQUE_KEY_SEPARATOR));
        }

        List<Selection> list = new ArrayList<>();
        for (String field : fields) {
            if (field.equals("key")) {
                list.add(((split == null) ? getCompoundJoinedPath(root, key): joinKeys(builder, root, split)).alias("key"));
            }
            else {
                String f = mappings.get(field);
                if (f == null) {
                    f = field;
                }
                list.add(getCompoundJoinedPath(root, f).alias(field));
            }
        }
        query.multiselect(list.toArray(new Selection[]{}));
        query = buildQuery(builder, query, criteria);
        TypedQuery<Tuple> typedQuery = em.createQuery(query);
        applyPagination(typedQuery, criteria);
        return typedQuery.getResultList();
    }

    private static Expression<String> joinKeys(CriteriaBuilder builder, Root root, String[] split) {
        Expression<String> result = null;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            Path path = getCompoundJoinedPath(root, s);
            Expression<String> temp = path;
            if (path.getJavaType() == LocalDate.class || path.getJavaType() == Date.class || Number.class.isAssignableFrom(path.getJavaType())) {
                temp = path.as(String.class);
            }
            if (i == 0) {
                result = temp;
            }
            else {
                result = builder.concat(result, GLOBAL_UNIQUE_KEY_SEPARATOR);
                result = builder.concat(result, temp);
            }
        }
        return result;
    }

    public static <T> List<T> query(EntityManager em, Class<T> clazz, Criteria criteria) {
        TypedQuery<T> typedQuery = em.createQuery(buildQuery(em.getCriteriaBuilder(), clazz, criteria));
        applyPagination(typedQuery, criteria);
        return typedQuery.getResultList();
    }

    public static <T> List<T> query(EntityManager em, CriteriaQuery<T> query, Criteria criteria) {
        TypedQuery<T> typedQuery = em.createQuery(buildQuery(em.getCriteriaBuilder(), query, criteria));
        applyPagination(typedQuery, criteria);
        return typedQuery.getResultList();
    }

    public static <T> long count(EntityManager em, Class<T> clazz, Criteria criteria) {
        return em.createQuery(buildCountQuery(em.getCriteriaBuilder(), clazz, criteria)).getSingleResult();
    }

    public static javax.persistence.criteria.Predicate[] buildPredicates(
            CriteriaBuilder b, CriteriaQuery<?> cq, List<org.spring.cloud.demo.registration.common.criteria.Predicate> predicates, boolean reserveHead) {
        // ReserveHead here is a performance optimization. When buildPredicates() is called with a cq that
        //  already has a predicate, that predicate needs to be carried along. This reserveHead is for that
        //  purpose: it reserves one extra space at the head of the result array for that predicate, otherwise
        //  we would have to allocate and copy the array in QueryBuilder.buildQuery().
        //
        // See also QueryBuilder.buildQuery()
        //
        int offset = reserveHead ? 1 : 0;
        javax.persistence.criteria.Predicate[] r = new javax.persistence.criteria.Predicate[predicates.size() + offset];
        for (int i = 0; i < predicates.size(); ++i) {
            r[i + offset] = buildPredicate(b, cq, predicates.get(i));
        }
        return r;
    }

    public static javax.persistence.criteria.Predicate[] buildPredicates(
            CriteriaBuilder b, CriteriaQuery<?> cq, List<Predicate> predicates) {
        return buildPredicates(b, cq, predicates, false);
    }

    public static javax.persistence.criteria.Predicate buildPredicate(CriteriaBuilder b, CriteriaQuery<?> cq, Predicate p) {
        Predicate.PredicateOperator op = p.getOp();
        if (op == AND || op == OR) {
            javax.persistence.criteria.Predicate[] subs = buildPredicates(b, cq, ((CompoundPredicate) p).getPredicates());
            return op == AND ? b.and(subs) : b.or(subs);
        }

        SimplePredicate cp = (SimplePredicate) p;
        Path e = getCompoundJoinedPath(JpaCriteriaHelper.getRoot(cq), cp.getName());
        Object value = cp.getValue();
        Object convertedValue = convertValueToTargetType(cp.getName(), value, e.getJavaType());

        switch (op) {
            case EQ:
                return value == null ? b.isNull(e) : b.equal(e, convertedValue);
            case IN:
                return e.in(buildInQuery(cp.getName(), e, convertedValue));
            case NE:
                return value == null ? b.isNotNull(e) : b.notEqual(e, convertedValue);
            case GE:
                return b.greaterThanOrEqualTo((Expression<? extends Comparable>) e, (Comparable) convertedValue);
            case GT:
                return b.greaterThan((Expression<? extends Comparable>) e, (Comparable) convertedValue);
            case LE:
                return b.lessThanOrEqualTo((Expression<? extends Comparable>) e, (Comparable) convertedValue);
            case LT:
                return b.lessThan((Expression<? extends Comparable>) e, (Comparable) convertedValue);
            case LIKE:
                return buildLike(b, cp, e);
            default:
                throw new UnsupportedOperationException(p.getOp().toString());
        }
    }

    private static Collection buildInQuery(String fieldName, Path e, Object values) {
        if (!(values instanceof Collection)) {
           // throw new Exception(e.toString());
        }
        List list = (List) ((Collection) values).stream()
                .map(o -> convertValueToTargetType(fieldName, o, e.getJavaType()))
                .collect(Collectors.toList());
        return list;
    }

    private static javax.persistence.criteria.Predicate buildLike(CriteriaBuilder b, SimplePredicate cp, Path e) {
        if (!e.getJavaType().equals(String.class)) {
            throw new IllegalArgumentException(String.format("[ComparisonPredicate::LIKE] Field %s is not String, actual type: %s", cp.getName(), e.getJavaType().getName()));
        }
        if (!(cp.getValue() instanceof String)) {
            throw new IllegalArgumentException(String.format("[ComparisonPredicate::LIKE] Pattern %s for Field %s is not String", cp.getValue(), cp.getName()));
        }
        return b.like((Expression<String>) e, (String) cp.getValue());
    }

    /**
     * Apply pagination
     *
     * @param typedQuery
     * @param criteria
     */
    public static void applyPagination(TypedQuery<?> typedQuery, Criteria criteria) {
        if (criteria == null) {
            return;
        }
        if (criteria.getOffset() != null) {
            typedQuery.setFirstResult(criteria.getOffset());
        }
        if (criteria.getSize() != null) {
            typedQuery.setMaxResults(criteria.getSize());
        }
    }
}
