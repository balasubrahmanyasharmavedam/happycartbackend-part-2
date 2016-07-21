package com.subbu.happycartbackend.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.subbu.happycartbackend.dao.CategoryDAO;
import com.subbu.happycartbackend.dao.CategoryDAOImpl;
import com.subbu.happycartbackend.dao.SupplierDAO;
import com.subbu.happycartbackend.dao.SupplierDAOImpl;
import com.subbu.happycartbackend.model.Category;
import com.subbu.happycartbackend.model.Supplier;
@Configuration
@ComponentScan("com.subbu.happycartbackend")
@EnableTransactionManagement


public class ApplicationContextConfig {
	
	@Bean(name="dataSource")
	public DataSource getDataSource() {
	      DriverManagerDataSource dataSource =new DriverManagerDataSource();
	      dataSource.setDriverClassName("org.h2.Driver");
	      dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
	      
	      dataSource.setUsername("sa");
	      dataSource.setPassword("sa");
	      return dataSource;
	}
	
	
	
	private Properties getHibernateProperties(){
		
		Properties properties =new Properties();
		properties.put("hibernate.show_sql","true");
		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		return properties;
		
	}
	
	@Autowired
	@Bean(name="sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource){
		
		LocalSessionFactoryBuilder sessionBuilder =new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		return sessionBuilder.buildSessionFactory();
		
	}

	
	@Autowired
	@Bean(name="transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
		HibernateTransactionManager transactionManager =new HibernateTransactionManager(sessionFactory);
		return transactionManager;
		
	}
	
	
	@Autowired
	@Bean(name="categoryDao")
	public CategoryDAO getCategoryDao(SessionFactory sessionFactory){
		return new CategoryDAOImpl(sessionFactory);
		
	}
	
	@Autowired
	@Bean(name="supplierDao")
	public SupplierDAO getSupplierDao(SessionFactory sessionFactory){
		return new SupplierDAOImpl(sessionFactory);
		
	}
	
	

}
