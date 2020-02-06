package org.spring.cloud.demo.registration.service;

import org.junit.Test;
import org.spring.cloud.demo.registration.AbstractJpaTest;
import org.spring.cloud.demo.registration.entity.product.Product;
import org.spring.cloud.demo.registration.util.TestDataUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class ProductServiceTest extends AbstractJpaTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testSave() {
        assertNotNull(productService);
        List<Product> products = TestDataUtil.getProductList();
        products = productService.save(products);
        assertNotNull(products);

    }

}
