package lv.javaguru.java2.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;


/**
 * Created by Vitolds on 12/8/2016.
 */
@Configuration
@ComponentScan(basePackages = {"lv.javaguru.java2"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"lv.javaguru.java2.database.springJPA"})
public class SpringAppConfig {
    private static final String DATABASE_PROPERTIES_FILE = "database.properties";

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
                                                                       @Value("${hibernate.packagesToScan}") String packagesToScan,
                                                                       @Qualifier("hibernateProperties") Properties properties) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer prodPropertiesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer p = new PropertySourcesPlaceholderConfigurer();
        Resource[] resourceLocations = new Resource[] {
                new ClassPathResource(DATABASE_PROPERTIES_FILE)
        };
        p.setLocations(resourceLocations);
        return p;
    }

    @Bean
    public Properties hibernateProperties(
            @Value("${hibernate.dialect}") String dialect,
            @Value("${hibernate.show_sql}") boolean showSql,
            @Value("${hibernate.format_sql}") boolean formatSql,
            @Value("${hibernate.hbm2ddl.auto}") String hbm2ddl) {

        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);

        return properties;
    }
//
    @Bean(destroyMethod = "close")
    public DataSource dataSource(
            @Value("${driverClass}") String driver,
            @Value("${jdbcUrl}") String url,
            @Value("${database.userName}") String user,
            @Value("${password}") String password) throws PropertyVetoException {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setDefaultAutoCommit(false);

        return dataSource;
    }
//
//    @Bean
//    public SessionFactory sessionFactory(DataSource dataSource,
//                                         @Value("${hibernate.packagesToScan}") String packagesToScan,
//                                         @Qualifier("hibernateProperties") Properties properties) throws Exception {
//
//        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
//        sessionFactoryBean.setDataSource(dataSource);
//        sessionFactoryBean.setPackagesToScan(packagesToScan);
//        sessionFactoryBean.setHibernateProperties(properties);
//        sessionFactoryBean.afterPropertiesSet();
//        return sessionFactoryBean.getObject();
//    }
//
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
