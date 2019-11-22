package org.spring.cloud.demo.registration.service.impl;

import org.spring.cloud.demo.registration.entity.product.Product;
import org.spring.cloud.demo.registration.repository.ProductRepository;
import org.spring.cloud.demo.registration.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> save(List<Product> productList) {
       return productRepository.saveAll(productList);
    }
}
