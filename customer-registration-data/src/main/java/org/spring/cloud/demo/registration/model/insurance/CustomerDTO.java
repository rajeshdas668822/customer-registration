package org.spring.cloud.demo.registration.model.insurance;

import lombok.Data;

@Data
public class CustomerDTO extends BaseDTO {

    private Integer customerId;

    private String customerRef;

    private String fistName;

    private String lastName;

    private String email;

    private String mobile;

    private String homePhone;
}
