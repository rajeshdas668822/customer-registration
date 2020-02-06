package org.spring.cloud.demo.registration.common.util;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.path.SingularAttributePath;

import javax.persistence.ElementCollection;
import javax.persistence.criteria.*;
import java.lang.reflect.Field;

import static org.spring.cloud.demo.registration.common.CommonConstatnt.EMPTY_STRING;

@Slf4j
public class JpaCriteriaHelper {

    private static final String REGEX_INDEX_MATCH = "\\[[\\d]+\\]";

    public static final String REGEX_DOT_SEPARATOR = "\\.";

    /**
     * Find joined the path
     *
     * @return Path of compound field to the primitive type
     */
    public static Path getCompoundJoinedPath(Root<?> rootPath, String fieldName) {
        if (fieldName == null) {
            return null;
        }
        String[] compoundField = splitByDot(fieldName);

        Join<?, ?> join;

        if (compoundField.length == 1) {
            Path<Object> path = rootPath.get(compoundField[0]);
            if (path instanceof SingularAttributePath) {
                return path;
            }
            try {
                Field field = rootPath.getJavaType().getDeclaredField(fieldName);
                ElementCollection annotation = field.getAnnotation(ElementCollection.class);
                if (annotation != null) {
                    CriteriaJoinType criteriaJoinType = field.getAnnotation(CriteriaJoinType.class);
                    return rootPath.join(fieldName, criteriaJoinType == null ? JoinType.INNER : criteriaJoinType.value());
                }
            } catch (NoSuchFieldException e) {
                log.error("Field [{}] not found in Entity {}", fieldName, rootPath.getJavaType(), e);
            }
            return path;
        } else {
            join = reuseJoin(rootPath, compoundField[0]);
        }

        for (int i = 1; i < compoundField.length; i++) {
            if (i < (compoundField.length - 1)) {
                join = reuseJoin(join, compoundField[i]);
            } else {
                return join.get(compoundField[i]);
            }
        }

        return null;
    }

    // trying to find already existing joins to reuse

    /**
     * Reuse the existing join
     *
     * @return Join
     */
    public static Join reuseJoin(From<?, ?> path, String fieldName) {
        for (Join<?, ?> join : path.getJoins()) {
            if (join.getAttribute().getName().equals(fieldName)) {
                log.debug("Reusing existing join for field {}", fieldName);
                return join;
            }
        }
        Field field = null;
        try {
            field = path.getJavaType().getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            log.error("No such field [{}], using INNER join for it", fieldName, e);
        }

        JoinType joinType = null;
        if (field != null) {
            CriteriaJoinType criteriaJoinType = field.getAnnotation(CriteriaJoinType.class);
            if (criteriaJoinType != null) {
                joinType = criteriaJoinType.value();
            }
        }

        return path.join(fieldName, null == joinType ? JoinType.INNER : joinType);
    }

    /**
     * Remove indexed property from string "[0]" and split by dot
     *
     * @return String[]
     */
    public static String[] splitByDot(String fieldName) {
        String name = fieldName.replaceAll(REGEX_INDEX_MATCH, EMPTY_STRING);
        return name.split(REGEX_DOT_SEPARATOR);
    }

    public static Root<?> getRoot(CriteriaQuery<?> cq) {
        return cq.getRoots().iterator().next(); // assume only one root
    }

    public static javax.persistence.criteria.Order buildOrder(CriteriaBuilder b, CriteriaQuery<?> cq, org.spring.cloud.demo.registration.common.criteria.Order order) {
        Path e = getCompoundJoinedPath(getRoot(cq), order.getName());
        return order.isAsc() ? b.asc(e) : b.desc(e);
    }
}
