package com.bangahu.main.common.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
	private final Environment env;

	@Bean(name = "jpaVendorAdapter")
	public HibernateJpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(false);
		adapter.setShowSql(true);
		return adapter;
	}
	
	@Primary
	@Bean(name = "masterEntityManager")
	public LocalContainerEntityManagerFactoryBean masterEntityManager(
			@Qualifier("masterDataSource") DataSource dataSource,
			@Qualifier("jpaVendorAdapter") HibernateJpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan(new String[] { "com.bangahu.main.model.entity.master" });
		em.setDataSource(dataSource);
		em.setJpaVendorAdapter(jpaVendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		em.setJpaPropertyMap(properties);
		return em;
	}
	
	@Bean(name = "secondEntityManager")
	public LocalContainerEntityManagerFactoryBean secondEntityManager(
			@Qualifier("masterDataSource") DataSource dataSource,
			@Qualifier("jpaVendorAdapter") HibernateJpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setPackagesToScan(new String[] { "com.bangahu.main.model.entity.second" });
		em.setDataSource(dataSource);
		em.setJpaVendorAdapter(jpaVendorAdapter);
		
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", true);
		properties.put("hibernate.format_sql", true);
		em.setJpaPropertyMap(properties);
		return em;
	}
	
}
