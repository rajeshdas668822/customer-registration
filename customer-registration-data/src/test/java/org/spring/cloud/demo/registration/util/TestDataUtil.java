package org.spring.cloud.demo.registration.util;

import org.spring.cloud.demo.registration.entity.insurance.Address;
import org.spring.cloud.demo.registration.entity.insurance.Customer;
import org.spring.cloud.demo.registration.entity.product.Product;

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

    public static List<Product> getProductList(){
        List<Product> products = new ArrayList<>();

       Product product = new Product();
        product.setDescription("Maintenance Of Chronic Medical Conditions");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Hospital Charges, Medical Practitioner and Specialist Fee");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Emergency Ambulance Transportation");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Parent Accommodation");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Renal failure And Renal Dialysis");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Organ Transplant");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        product = new Product();
        product.setDescription("Cancer Treatment");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        product = new Product();
        product.setDescription("Organ Transplant");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        product = new Product();
        product.setDescription("Pregnancy and Childbirth Medical Conditions");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);


        product = new Product();
        product.setDescription("New Born Cover");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);


        product = new Product();
        product.setDescription("Hospital Accommodation for New Born Accompanying their Mother");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        product = new Product();
        product.setDescription("Congenital Disorder");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);


        product = new Product();
        product.setDescription("Reconstructive Surgery");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);


        product = new Product();
        product.setDescription("In-Patient Emergency Dental Treatment");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        product = new Product();
        product.setDescription("Rehabilitation");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("In-Patient Psychiatric Treatment");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Terminal Illness");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);
        product = new Product();
        product.setDescription("Mortal Remains");
        product.setProductType("inPes");
        product.setProductRef(DataUtil.getRefByType("PRD"));
        products.add(product);

        return products;

    }





}

