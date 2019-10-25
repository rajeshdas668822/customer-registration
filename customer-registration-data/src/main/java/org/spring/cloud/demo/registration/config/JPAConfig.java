package org.spring.cloud.demo.registration.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan("org.spring.cloud.demo.registration.entity")
public class JPAConfig {


    @Value("${entity.packagetoscan:org.spring.cloud.demo.registration.entity}")
    private String packageToScan;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName("org.postgresql.Driver");
        driver.setUrl("jdbc:postgresql://localhost:5432/customer");
        driver.setUsername("postgres");
        driver.setPassword("Password1");
        return driver;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean  entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(packageToScan);
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }


    public Properties additionalProperties(){
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("hibernate.default_schema", "public");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation", "true");




         /*<entry key="hibernate.default_schema" value="${jdbc.schemaName}" />
                <entry key="hibernate.hbm2ddl.auto" value="${hibernate.hbm2ddl.auto:validate}" />
                <entry key="hibernate.dialect" value="${hibernate.dialect:com.numerix.oneview.base.data.dialect.OneviewSQL2012Dialect}" />
                <entry key="hibernate.show_sql" value="${hibernate.show_sql:false}" />
                <entry key="hibernate.format_sql" value="${hibernate.format_sql:false}" />
                <entry key="hibernate.globally_quoted_identifiers" value="${hibernate.globally_quoted_identifiers:true}" />
                <entry key="hibernate.order_inserts" value="${hibernate.order_inserts:true}" />
                <entry key="hibernate.order_updates" value="${hibernate.order_updates:true}" />
                <entry key="hibernate.jdbc.batch_size" value="${hibernate.jdbc.batch_size:100}" />
                <entry key="hibernate.cache.use_second_level_cache" value="${hibernate.cache.use_second_level_cache:false}" />
                <entry key="hibernate.cache.use_query_cache" value="${hibernate.cache.use_query_cache:false}" />
                <entry key="hibernate.cache.region.factory_class" value="${hibernate.cache.region.factory_class:}" />
                <entry key="hibernate.cache.use_minimal_puts" value="true" />
                <entry key="hibernate.generate_statistics" value="false" />
                <entry key="hibernate.cache.use_structured_entries" value="true" />
                <entry key="javax.persistence.sharedCache.mode" value="ENABLE_SELECTIVE" />
                <entry key="hibernate.cache.hazelcast.shutdown_on_session_factory_close" value="true" />
                <entry key="hibernate.enable_lazy_load_no_trans" value="${hibernate.enable_lazy_load_no_trans:true}" />
                <entry key="hibernate.event.merge.entity_copy_observer" value="allow" />
                <entry key="hibernate.session_factory.interceptor">
                    <ref bean="hibernateInterceptor" />
                </entry>*/
        return properties;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
