package org.spring.cloud.demo.registration.repository;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface AuditReaderRepository<T> {
    List<T> getAllAudit(Class<T> valueType);
}
