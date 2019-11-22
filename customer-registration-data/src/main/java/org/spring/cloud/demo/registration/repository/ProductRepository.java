package org.spring.cloud.demo.registration.repository;

import org.spring.cloud.demo.registration.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}
