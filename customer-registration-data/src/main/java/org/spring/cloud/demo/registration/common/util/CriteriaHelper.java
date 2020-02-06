package org.spring.cloud.demo.registration.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.spring.cloud.demo.registration.common.exception.ApplicationException;
import org.spring.cloud.demo.registration.common.exception.CommonErrorCode;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
public class CriteriaHelper {

    public static Object convertValueToTargetType(String fieldName, Object value, Class targetType) {
        if (value == null) {
            return value;
        }
        if (value instanceof String) {
            if (targetType.equals(LocalDate.class)) {
                return DateUtil.toLocalDate((String) value);
            } else if (targetType.equals(LocalDateTime.class)) {
                return DateUtil.toLocalDateTime((String) value);
            } else if (targetType.equals(Long.class) && DateUtil.determineDateFormat((String) value) != null) {
                return DateUtil.toEpoch(DateUtil.toLocalDateTime((String) value));
            } else if (Number.class.isAssignableFrom(targetType) && !NumberUtils.isDigits(value.toString())) {
                throw new ApplicationException(CommonErrorCode.NOT_NUMBER, fieldName);
            }
            return value;
        } else if (value instanceof Long) {
            if (targetType.equals(LocalDateTime.class)) {
                return DateUtil.toLocalDateTime((Long) value);
            }
            if (targetType.equals(Integer.class)) {
                return ((Long) value).intValue();
            }
            return value;
        } else if (value instanceof LocalDateTime) {
            if (targetType.equals(Long.class)) {
                return DateUtil.toEpoch((LocalDateTime) value);
            }
            return value;
        }

        // when they are not compatible but they are both numbers, try conversion
        targetType = ClassUtils.primitiveToWrapper(targetType);
        if (!targetType.isAssignableFrom(value.getClass()) && value instanceof Number && Number.class.isAssignableFrom(targetType)) {
            try {
                Constructor cons = targetType.getConstructor(String.class);
                return cons.newInstance(value.toString());
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                log.warn(String.format("Failed to convert value %s to targetType %s", value, targetType.getName()), e);
            }
        }
        return value;
    }
}
