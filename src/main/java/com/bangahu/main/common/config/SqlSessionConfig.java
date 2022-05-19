package com.bangahu.main.common.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class SqlSessionConfig {
	@Primary
	@MapperScan(value = "com.bangahu.main.dao.master.sqlmapper", sqlSessionFactoryRef = "masterSqlSessionFactory")
	public class MasterSqlSessionTemplate {
		@Bean(name="masterSqlSessionFactory")
		public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource, ApplicationContext applicationContext) throws Exception {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setMapperLocations(applicationContext.getResources("classpath:mybatis/master/*.xml"));
			sessionFactory.setConfigLocation(applicationContext.getResource("classpath:mybatis/mybatis-config.xml"));
			sessionFactory.setTypeAliasesPackage("com.bangahu.main.model.domain");
			return sessionFactory.getObject();
		}
		
	    @Bean(name="masterSqlSessionTemplate")
	    public SqlSessionTemplate masterSqlSessionTemplate (
	            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessinFactory) throws Exception {
	        return new SqlSessionTemplate(sqlSessinFactory);
	    }
	    
	    @Bean(name="masterSqlSessionTransactionManager")
	    public DataSourceTransactionManager masterSqlSessionTransactionManager(@Qualifier("masterDataSource") DataSource dataSource) {
	    	DataSourceTransactionManager manager = new DataSourceTransactionManager();
	    	manager.setDataSource(dataSource); 
	    	return manager;
	    }
	}
	
	@MapperScan(value = "com.bangahu.main.dao.second.sqlmapper", sqlSessionFactoryRef = "secondSqlSessionFactory")
	public class SecondSqlSessionTemplate {
		@Bean(name="secondSqlSessionFactory")
		public SqlSessionFactory secondSqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource) throws Exception {
			SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
			sessionFactory.setDataSource(dataSource);
			sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/second/*.xml"));
			sessionFactory.setTypeAliasesPackage("com.bangahu.main.model.domain");
			sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));
			return sessionFactory.getObject();
		}
		
	    @Bean(name="secondSqlSessionTemplate")
	    public SqlSessionTemplate secondSqlSessionTemplate (
	            @Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessinFactory) throws Exception {
	        return new SqlSessionTemplate(sqlSessinFactory);
	    }
	    
	    @Bean(name="secondSqlSessionTransactionManager")
	    public DataSourceTransactionManager masterSqlSessionTransactionManager(@Qualifier("secondDataSource") DataSource dataSource) {
	    	DataSourceTransactionManager manager = new DataSourceTransactionManager();
	    	manager.setDataSource(dataSource); 
	    	return manager;
	    }
	}
}
