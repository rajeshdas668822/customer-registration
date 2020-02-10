package org.spring.cloud.demo.registration.repository.generic;

import org.spring.cloud.demo.registration.common.criteria.Criteria;

import java.util.List;

public interface DefaultRepository  {
    <T> List<T> getAllAudit(Class<T> valueType);

    <T> T insert(T entity);

    <T> List<T> getByCriteria(Class<T> type, Criteria criteria);

    <T> List<T> getAll(Class<T> type);

    <T> T save(T entity);

    <T> void deleteAll(Class<T> type);

    <T> T getById(Class<T> type, Object id);

    <T> T delete(T entity);



}
