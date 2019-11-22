package org.spring.cloud.demo.registration.repository.impl;

import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.spring.cloud.demo.registration.repository.AuditReaderRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


public class AuditReaderRepositoryImpl implements AuditReaderRepository {


    private EntityManager em;

    public AuditReaderRepositoryImpl(EntityManager em){
        this.em = em;
    }

    @Override
    @Transactional
    public List getAllAudit(Class valueType) {
        AuditReader auditReader = AuditReaderFactory.get(em);
        return auditReader.createQuery()
                .forRevisionsOfEntity(valueType, true, true).getResultList();
    }




}
