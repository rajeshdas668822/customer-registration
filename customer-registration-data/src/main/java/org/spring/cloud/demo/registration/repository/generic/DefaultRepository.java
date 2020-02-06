package org.spring.cloud.demo.registration.repository.generic;

import java.util.List;

public interface DefaultRepository  {
    <T> List<T> getAllAudit(Class<T> valueType);
}
