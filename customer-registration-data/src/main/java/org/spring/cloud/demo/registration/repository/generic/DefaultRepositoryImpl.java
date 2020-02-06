package org.spring.cloud.demo.registration.repository.generic;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
