package org.spring.cloud.demo.registration.mapper.insurance;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.model.insurance.CustomerDTO;

@Mapper(config = DefaultMapperConfig.class)
public abstract class CustomerMapper extends AbstractDTOMapper<CustomerDTO, Customer> {
    @Override
    public abstract CustomerDTO toBean(Customer customer);

    @Override
    public abstract Customer toEntity(CustomerDTO customerDTO);

    @AfterMapping
    protected void updateEntityAfterMapping(CustomerDTO dto, @MappingTarget Customer entity) {

    }


    @AfterMapping
    protected void updateBeanAfterMapping(Customer entity, @MappingTarget CustomerDTO dto) {

    }

}
