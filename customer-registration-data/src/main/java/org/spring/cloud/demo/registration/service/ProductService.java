package org.spring.cloud.demo.registration.service;

        import org.spring.cloud.demo.registration.entity.product.Product;

        import java.util.List;

public interface ProductService {
    List<Product> save(List<Product> productList);
}
