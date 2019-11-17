package org.spring.cloud.demo.registration.util;

import org.spring.cloud.demo.registration.entity.insurance.Address;
import org.spring.cloud.demo.registration.entity.insurance.Customer;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static List<Address> getAddressData(){
        List<Address> addresses = new ArrayList<>();
        Address address = new Address();
        address.setCity("Baripada");
        address.setCountry("India");
        address.setPinCode("757001");
        address.setState("Orissa");
        address.setUnit("#04-1782");
        addresses.add(address);

        address = new Address();
        address.setCity("Bedok");
        address.setCountry("Singapore");
        address.setPinCode("472613");
        address.setState("Bedok Central");
        address.setUnit("#04-1783");
        addresses.add(address);
        return addresses;
    }

    public static Customer getCustomer(){
        Customer customer = new Customer();
        customer.setCustomerRef(DataUtil.getRefByType("CUST"));
        customer.setEmail("rajeshranjan.d@gmail.com");
        customer.setFistName("Rajesh");
        customer.setLastName("Das");
        customer.setHomePhone("+65-94592685");
         List<Address> addresses = getAddressData();
        addresses.forEach(address -> {
            address.setCustomer(customer);
        });
        customer.setAddress(addresses);
        return customer;
    }
}

