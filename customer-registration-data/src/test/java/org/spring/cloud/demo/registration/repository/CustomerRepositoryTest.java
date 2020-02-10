package org.spring.cloud.demo.registration.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.spring.cloud.demo.registration.AbstractJpaTest;
import org.spring.cloud.demo.registration.common.criteria.Criteria;
import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.repository.generic.DefaultRepository;
import org.spring.cloud.demo.registration.util.TestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class CustomerRepositoryTest extends AbstractJpaTest {

    @Autowired
    DefaultRepository defaultRepository;

    @Before
    public void testSaveCustomerRegistration(){
        assertNotNull(defaultRepository);
        Customer customer = TestDataUtil.getCustomer();
        customer = defaultRepository.insert(customer);
        assertNotNull(customer.getCustomerId());
    }

   @Test
    public void testFindCustomer(){
        List<Customer> customers = defaultRepository.getAll(Customer.class);
        assertNotNull(customers);
        assertTrue(customers.size()>=1);
        assertNotNull(customers.get(0).getAddress());
        assertEquals(1,customers.get(0).getAddress().size());

    }

    @Test
    public void testByCriteria(){
        Criteria criteria = new Criteria();
        criteria.eq("email","rajeshranjan.d@gmail.com");
        List<Customer> customers = defaultRepository.getByCriteria(Customer.class,criteria);
        assertTrue(customers.size()>=1);
        assertNotNull(customers.get(0).getAddress());
        assertEquals(1,customers.get(0).getAddress().size());
    }

   @Test
    public void testPartialDeleteCustomer(){
        List<Customer> customers = defaultRepository.getAll(Customer.class);
        assertNotNull(customers);
        Customer customer = customers.stream().filter(p->!CollectionUtils.isEmpty(p.getAddress())).findFirst().get();
        customer.getAddress().remove(0);
        customer = defaultRepository.save(customer);
        assertNotNull(customer.getAddress());
        assertEquals(customer.getAddress().size(),1);

    }


    @Test
    public void testDeleteAll(){
        defaultRepository.deleteAll(Customer.class);
        List<Customer> customers = defaultRepository.getAll(Customer.class);
        Assert.assertNotNull(customers);

    }

}
