package org.spring.cloud.demo.registration.mapper.insurance;

public abstract class AbstractDTOMapper<B,E>{
    public static final String TO_DTO_MAPPING_NAME = "toDTO";
    public static final String TO_ENTITY_MAPPING_NAME = "toEntity";
    public static final String TO_DTOS_MAPPING_NAME = "toDTOs";

    public abstract B toBean(E var1);
    public abstract E toEntity(B var1);
}
