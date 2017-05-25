package net.saama.spring.config;

import java.util.Properties;

import javax.sql.DataSource;

import net.saama.spring.util.AppConstants;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "net.saama.spring" })
@PropertySource(value = { "classpath:app.properties" })
public class HibernateConfiguration {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "net.saama.spring" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
	
    @Bean
    public DataSource dataSource() {
    	String database = environment.getRequiredProperty(AppConstants.PROP_APP_DATABASE);
    	
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty(database + AppConstants.DOT + AppConstants.PROP_JDBC_DRIVERCLASSNAME));
        dataSource.setUrl(environment.getRequiredProperty(database + AppConstants.DOT + AppConstants.PROP_JDBC_URL));
        dataSource.setUsername(environment.getRequiredProperty(database + AppConstants.DOT + AppConstants.PROP_JDBC_USERNAME));
        dataSource.setPassword(environment.getRequiredProperty(database + AppConstants.DOT + AppConstants.PROP_JDBC_PASSWORD));
        return dataSource;
    }
    
    private Properties hibernateProperties() {
    	String database = environment.getRequiredProperty(AppConstants.PROP_APP_DATABASE);
    	
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty(database + AppConstants.DOT + AppConstants.PROP_HIBERNATE_DIALECT));
        properties.put("hibernate.show_sql", environment.getRequiredProperty(AppConstants.PROP_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.format_sql", environment.getRequiredProperty(AppConstants.PROP_HIBERNATE_FORMAT_SQL));
        properties.put("hibernate.default_schema", environment.getRequiredProperty(AppConstants.PROP_HIBERNATE_DEFAULT_SCHEMA));
        return properties;        
    }
    
	@Bean
    @Autowired
    public HibernateTransactionManager transactionManager(SessionFactory s) {
       HibernateTransactionManager txManager = new HibernateTransactionManager();
       txManager.setSessionFactory(s);
       return txManager;
    }
}

