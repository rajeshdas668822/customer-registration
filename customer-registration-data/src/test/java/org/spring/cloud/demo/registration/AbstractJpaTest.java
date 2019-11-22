package org.spring.cloud.demo.registration;

import org.junit.runner.RunWith;
import org.spring.cloud.demo.registration.config.JpaConfig;
import org.spring.cloud.demo.registration.config.JpaTestConfig;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        initializers = {DataContextInitializer.class}
)
@EnableJpaRepositories(basePackages = "org.spring.cloud.demo.registration")
@SpringBootConfiguration
@ComponentScan(value = "org.spring.cloud.demo.registration")
@SpringBootTest(classes = {JpaConfig.class,JpaTestConfig.class})
public abstract class AbstractJpaTest {

}
