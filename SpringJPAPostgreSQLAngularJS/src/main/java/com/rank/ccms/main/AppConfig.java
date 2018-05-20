package com.rank.ccms.main;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

@Configuration
public class AppConfig {
	
 @Value("${spring.database.driverClassName}")
 private String DB_DRIVER;

 @Value("${spring.datasource.username}")
 private String DB_USERNAME;

 @Value("${spring.datasource.password}")
 private String DB_PASSWORD;

 @Value("${spring.datasource.url}")
 private String DB_URL;

 @Value("${spring.jpa.properties.hibernate.dialect}")
 private String HIBERNATE_DIALECT;

 @Value("${spring.jpa.show-sql}")
 private String HIBERNATE_SHOW_SQL;

 /*@Value("${spring.jpa.hibernate.ddl-auto}")
 private String HIBERNATE_HBM2DDL_AUTO;*/

 @Value("${entitymanager.packagesToScan}")
 private String ENTITYMANAGER_PACKAGES_TO_SCAN;
 
 

 @Bean
 public DataSource dataSource() {
  DriverManagerDataSource dataSource = null;
  try {
   dataSource = new DriverManagerDataSource();
   dataSource.setDriverClassName(DB_DRIVER);
   dataSource.setUrl(DB_URL);
   dataSource.setUsername(DB_USERNAME);
   dataSource.setPassword(DB_PASSWORD);
  } catch (Exception e) {
   e.getMessage();
  }
  return dataSource;
 }

 @Bean
 public LocalSessionFactoryBean sessionFactory() {
  LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
  sessionFactoryBean.setDataSource(dataSource());
  sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
  Properties hibernateProps = new Properties();
  hibernateProps.put("hibernate.dialect", HIBERNATE_DIALECT);
  hibernateProps.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
  //hibernateProps.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
 
  sessionFactoryBean.setHibernateProperties(hibernateProps);
  return sessionFactoryBean;
 }


}
