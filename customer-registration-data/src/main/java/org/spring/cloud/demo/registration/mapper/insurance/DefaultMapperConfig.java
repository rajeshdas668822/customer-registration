package org.spring.cloud.demo.registration.mapper.insurance;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValueMappingStrategy;

@MapperConfig(
        componentModel = "spring",
        nullValueMappingStrategy = NullValueMappingStrategy.RETURN_NULL
)
public class DefaultMapperConfig {
    public DefaultMapperConfig() {
    }
}
