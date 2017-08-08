package ru.ddd.lessons.service.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.persistenceunit.DefaultPersistenceUnitManager;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import ru.ddd.lessons.dbapi.JpaDao;
import ru.ddd.lessons.policy.Dao;
import ru.ddd.lessons.service.persistence.hibernate.BookCategoryRepositoryHibernate;
import ru.ddd.lessons.service.persistence.hibernate.BookRepositoryHibernate;
import ru.ddd.lessons.service.persistence.hibernate.StockDailyRecordRepositoryHibernate;
import ru.ddd.lessons.service.persistence.hibernate.StockRepositoryHibernate;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.BookCategoryRepository;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.BookRepository;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockDailyRecordRepository;
import ru.ddd.lessons.service.persistence.hibernate.interfaces.StockRepository;


import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.SharedCacheMode;
import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

    @PersistenceContext(unitName = "default")
    EntityManager entityManager;

    @Autowired
    @Bean("corpbotDataSource")
    public DataSource corpbotDataSource(Environment environment) {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("connection.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("connection.jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("connection.username"));
        dataSource.setPassword(environment.getRequiredProperty("connection.password"));
        return dataSource;
    }

    @Autowired
    @Bean("persistenceUnitManager")
    public DefaultPersistenceUnitManager persistenceUnitManager(
            @Qualifier("corpbotDataSource") DataSource corpbotDataSource) {
        DefaultPersistenceUnitManager unitManager = new DefaultPersistenceUnitManager();
        unitManager.setPersistenceXmlLocation(DefaultPersistenceUnitManager.DEFAULT_PERSISTENCE_XML_LOCATION);
        unitManager.setDefaultDataSource(corpbotDataSource);
        return unitManager;
    }

    @Autowired
    @Primary
    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory(
            @Qualifier("persistenceUnitManager") DefaultPersistenceUnitManager persistenceUnitManager,
            Environment environment) throws NamingException {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setPersistenceUnitName("default");
        factoryBean.setPersistenceUnitManager(persistenceUnitManager);
        String packagesToScan = environment.getRequiredProperty("model.packagesToScan");
        factoryBean.setPackagesToScan(packagesToScan.split(","));
        factoryBean.setJpaDialect(jpaDialect());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter());
        factoryBean.setJpaProperties(jpaProperties(environment));
        factoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        factoryBean.afterPropertiesSet();
        return factoryBean.getObject();
    }


    @Bean("baseDao")
    public Dao baseDao() {
        return new JpaDao(entityManager);
    }

    @Autowired
    @Bean("transactionManager")
    public JpaTransactionManager transactionManager(
            @Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    @Bean
    public JpaDialect jpaDialect() {
        return new HibernateJpaDialect();
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        return new HibernateJpaVendorAdapter();
    }

    @Autowired
    @Bean(name = "StockRepository")
    public StockRepository stockRepository(@Qualifier("baseDao") Dao baseDao) {
        return new StockRepositoryHibernate(baseDao);
    }

    @Autowired
    @Bean(name = "StockDailyRecordRepository")
    public StockDailyRecordRepository stockDailyRecordRepository(@Qualifier("baseDao") Dao baseDao) {
        return new StockDailyRecordRepositoryHibernate(baseDao);
    }

    @Autowired
    @Bean(name = "BookRepository")
    public BookRepository bookRepository(@Qualifier("baseDao") Dao baseDao) {
        return new BookRepositoryHibernate(baseDao);
    }

    @Autowired
    @Bean(name = "BookCategoryRepository")
    public BookCategoryRepository bookCategoryRepository(@Qualifier("baseDao") Dao baseDao) {
        return new BookCategoryRepositoryHibernate(baseDao);
    }

    private Properties jpaProperties(Environment environment) {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.jdbc.use_get_generated_keys", true);
        properties.put("hibernate.jdbc.use_streams_for_binary", true);
        return properties;
    }
}
