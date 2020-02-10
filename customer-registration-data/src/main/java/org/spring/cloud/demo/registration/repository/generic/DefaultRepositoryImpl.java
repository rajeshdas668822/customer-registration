package org.spring.cloud.demo.registration.repository.generic;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.spring.cloud.demo.registration.common.criteria.Criteria;
import org.spring.cloud.demo.registration.common.util.JpaQueryBuilder;
import org.springframework.data.util.ProxyUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Slf4j
@Repository
public class DefaultRepositoryImpl implements DefaultRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public <T> List<T> getAllAudit(Class<T> valueType) {
        AuditReader auditReader = AuditReaderFactory.get(em);
        return auditReader.createQuery()
                .forRevisionsOfEntity(valueType, true, true).getResultList();
    }

    @Override
    @Transactional
    public <T> T insert(T entity) {
        return em.merge(entity);
    }

    @Override
    @Transactional
    public <T> List<T> getByCriteria(Class<T> type, Criteria criteria) {
        if (criteria == null) {
            return getAll(type);
        }
        return JpaQueryBuilder.query(em, type, criteria);
    }


    @Override
    @Transactional
    public <T> List<T> getAll(Class<T> type) {
        return em.createQuery("Select t from " + type.getSimpleName() + " t").getResultList();
    }

    @Override
    @Transactional
    public <T> T save(T entity) {
        entity = mergeOrPersist(entity);
        em.flush();
        return entity;
    }

    @Override
    @Transactional
    public <T> void deleteAll(Class<T> type) {
        //em.createQuery("delete from " + type.getSimpleName()).executeUpdate();
        List<T> entities = getAll(type);
        for(T t : entities){
            delete(t);
        }
    }


    private <T> T mergeOrPersist(T entity) {
        Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);
        if (id == null) {
            insert(entity);
        } else {
            entity = em.merge(entity);
        }
        return entity;
    }

    @Override
    @Transactional
    public <T> T getById(Class<T> type, Object id) {
        return em.find(type, id);
    }



    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public   <T> T delete(T entity) {

        Assert.notNull(entity, "Entity must not be null!");
        Object id = em.getEntityManagerFactory().getPersistenceUnitUtil().getIdentifier(entity);

        if (id == null) {
            return entity;
        }

        Class<?> type = ProxyUtils.getUserClass(entity);

        T existing = (T) em.find(type, id);

        // if the entity to be deleted doesn't exist, delete is a NOOP
        if (existing == null) {
            return entity;
        }

        em.remove(em.contains(entity) ? entity : em.merge(entity));
        return entity;
    }

}
