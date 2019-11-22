package org.spring.cloud.demo.registration.model.insurance;

import lombok.Data;

import java.io.Serializable;

@Data
public abstract class BaseDTO implements Serializable {

    private long createdDate;

    private long modifiedDate;

    private String createdBy;

    private String modifiedBy;


}
