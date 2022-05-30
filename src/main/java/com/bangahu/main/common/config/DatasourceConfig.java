package com.bangahu.main.common.config;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatasourceConfig {
	@Primary
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.master")
	public DataSource masterDataSource() {
		//return DataSourceBuilder.create().type(HikariDataSource.class).build();
		return new HikariDataSource();
	}
	
	@Bean(name = "secondDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.second")
	public DataSource secondDataSource() {
		return new HikariDataSource();
	}
	
	@Primary
	@Bean(name = "masterDataSource")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.master")
	public DataSource masterXADataSource() {
//        Properties props = new Properties();
//        props.setProperty("url", "jdbc:postgresql://localhost:5432/postgres");
//        props.setProperty("user", "postgres");
//        props.setProperty("password", "postgres");
//        
//        AtomikosDataSourceBean dataSource = new AtomikosDataSourceBean();
//        dataSource.setUniqueResourceName("masterAtomikos");
//        dataSource.setXaDataSourceClassName("org.postgresql.xa.PGXADataSource");
//        dataSource.setXaProperties(props);
//        return dataSource;
		return new AtomikosDataSourceBean();
	}
	
	
	@Bean(name = "secondDataSource")
	@ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.second")
    public DataSource secondXADataSource() throws SQLException {
//        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
//        mysqlXADataSource.setURL("jdbc:mysql://localhost/test?autoReconnect=true");
//        mysqlXADataSource.setUser("eggseller");
//        mysqlXADataSource.setPassword("387421");
//        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);
//
//        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
//        xaDataSource.setXaDataSource(mysqlXADataSource);
//        xaDataSource.setUniqueResourceName(mysqlXADataSource.getResourceId());
//        return xaDataSource;
		return new AtomikosDataSourceBean();
    }
}
