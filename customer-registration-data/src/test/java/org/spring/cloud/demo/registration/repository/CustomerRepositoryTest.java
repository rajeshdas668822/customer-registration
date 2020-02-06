package org.spring.cloud.demo.registration.repository;

import org.junit.Ignore;
import org.junit.Test;
import org.spring.cloud.demo.registration.AbstractJpaTest;
import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.util.TestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;


public class CustomerRepositoryTest extends AbstractJpaTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void testSaveCustomerRegistration(){
        assertNotNull(customerRepository);
        Customer customer = TestDataUtil.getCustomer();
        customer = customerRepository.save(customer);

        assertNotNull(customer.getCustomerId());
    }

    @Test
    public void testFindCustomer(){
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
        assertTrue(customers.size()>=1);
        assertNotNull(customers.get(0).getAddress());
        assertEquals(1,customers.get(0).getAddress().size());

    }

    @Test
    public void testPartialDeleteCustomer(){
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);
        Customer customer = customers.stream().findFirst().get();
        customer.getAddress().remove(0);
        customer = customerRepository.saveAndFlush(customer);
        assertNotNull(customers.get(0).getAddress());
        assertEquals(customers.get(0).getAddress().size(),1);

    }


    @Test
    @Ignore
    public void testDeleteAll(){
        customerRepository.deleteAll();
        List<Customer> customers = customerRepository.findAll();
        assertNotNull(customers);

    }

}
