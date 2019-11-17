package org.spring.cloud.demo.registration.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@EnableTransactionManagement
public class JpaConfig {

    @Value("${entity.packagetoscan:org.spring.cloud.demo.registration.entity}")
    private String packageToScan;

    @Value("${jdbc.driverclass:org.postgresql.Driver}")
    private String driverClass;

    @Value("${jdbc.url:jdbc:postgresql://localhost:5432/customer}")
    private String url;

    @Value("${jdbc.dbuser:postgres}")
    private String dbuser;

    @Value("${jdbc.dbpassword:Password1}")
    private String dbpassword;

    @Value("${hibernate.hbm2ddl.auto:validate}")
    private String  ddlauto;

    @Value("${hibernate.default_schema:customer}")
    private String  defaultSchema;

    @Value("${hibernate.dialect:org.hibernate.dialect.PostgreSQL95Dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql:false}")
    private String showSql;

    @Value("${hibernate.format_sql:false}")
    private String formatSql;

    @Value("${hibernate.jdbc.batch_size:100}")
    private String hibernateJdbcBatchSize;

    @Value("${javax.persistence.sharedCache.mode:ENABLE_SELECTIVE}")
    private String sharedCacheMode;

    @Value("${org.hibernate.envers.audit_table_suffix:_audit}")
    private String auditTableSuffix;

    @Value("${org.hibernate.envers.revision_field_name:revision_id}")
    private String revisionFieldName;

    @Value("${org.hibernate.envers.revision_type_field_name:revision_type}")
    private String revisionFieldTypeName;

    @Value("${org.hibernate.envers.default_schema:customer_audit}")
    private String defaultAuditSchema;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource driver = new DriverManagerDataSource();
        driver.setDriverClassName(driverClass);
        driver.setUrl(url);
        driver.setUsername(dbuser);
        driver.setPassword(dbpassword);
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
        properties.setProperty("hibernate.hbm2ddl.auto",ddlauto);
        properties.setProperty("hibernate.default_schema",defaultSchema);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        properties.setProperty("hibernate.show_sql", showSql);
        properties.setProperty("hibernate.format_sql", formatSql);
        properties.setProperty("hibernate.jdbc.lob.non_contextual_creation","true" );
        properties.setProperty("hibernate.globally_quoted_identifiers", "true");
        properties.setProperty("hibernate.order_inserts", "true");
        properties.setProperty("hibernate.order_updates", "true");
        properties.setProperty("hibernate.jdbc.batch_size", hibernateJdbcBatchSize);

        properties.setProperty("hibernate.cache.use_second_level_cache", "false");
        //properties.setProperty("hibernate.cache.region.factory_class", "100");
        properties.setProperty("hibernate.cache.use_minimal_puts", "true");
        properties.setProperty("hibernate.generate_statistics", "false");
        properties.setProperty("hibernate.cache.use_structured_entries", "true");

        properties.setProperty("javax.persistence.sharedCache.mode", sharedCacheMode);
        properties.setProperty("hibernate.cache.hazelcast.shutdown_on_session_factory_close","true");
        properties.setProperty("hibernate.enable_lazy_load_no_trans", "true");
        properties.setProperty("hibernate.event.merge.entity_copy_observer", "allow");

        properties.setProperty("org.hibernate.envers.audit_table_suffix",auditTableSuffix);
        properties.setProperty("org.hibernate.envers.revision_field_name",revisionFieldName);
        properties.setProperty("org.hibernate.envers.revision_type_field_name",revisionFieldTypeName);

        properties.setProperty("org.hibernate.envers.default_schema",defaultAuditSchema);

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

    @Bean
    AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }
}
