package com.bangahu.main.common.config;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

import org.hibernate.engine.transaction.jta.platform.internal.AbstractJtaPlatform;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;

import lombok.Builder;

@EnableAutoConfiguration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@ComponentScan
@Configuration
public class TransactionConfig {
	@Bean(name = "dataSourceTxManager")
	public PlatformTransactionManager dataSourceTxManager(
			@Qualifier("masterDataSource") DataSource masterDataSource,
			@Qualifier("secondDataSource") DataSource secondDataSource) {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(masterDataSource);
		dataSourceTransactionManager.setDataSource(secondDataSource);
		return dataSourceTransactionManager;
	}

	@Bean(name = "jpaTxManager")
	public PlatformTransactionManager jpaTxManager(
			@Qualifier("masterEntityManager") EntityManagerFactory masterEntityManager,
			@Qualifier("secondEntityManager") EntityManagerFactory secondEntityManager) {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(masterEntityManager);
		jpaTransactionManager.setEntityManagerFactory(secondEntityManager);
		return jpaTransactionManager;
	}

	@Primary
	@Bean(name = "AtomikosTxManager")
	public PlatformTransactionManager AtomikosTxManager() throws Throwable {

		//JtaPlatform.transaction = jtaUserTransaction;
		//JtaPlatform.transactionManager = jtaUserTransactionManager;
		//return new JtaTransactionManager(jtaUserTransaction, jtaUserTransactionManager);
		UserTransaction userTransaction = atomikosUserTx();
		TransactionManager transactionManager = atomikosUserTxManager();
		JtaTransactionManager manager = new JtaTransactionManager(userTransaction, transactionManager);
		manager.setDefaultTimeout(60);
		manager.setRollbackOnCommitFailure(true);
		return manager;
	}

	@Bean
	public UserTransaction atomikosUserTx() throws SystemException {
		final UserTransaction atomikosUserTx = new UserTransactionImp();
		atomikosUserTx.setTransactionTimeout(60); // 1 min
		return atomikosUserTx;
	}

	@Bean
	public UserTransactionManager atomikosUserTxManager() {
		final UserTransactionManager atomikosUserTxManager = new UserTransactionManager();
		atomikosUserTxManager.setForceShutdown(false);
		return atomikosUserTxManager;
	}

	public static class JtaPlatform extends AbstractJtaPlatform {
		private static final long serialVersionUID = -1977857812255169536L;
		private static TransactionManager transactionManager;
		private static UserTransaction transaction;

		@Override
		protected TransactionManager locateTransactionManager() {
			return transactionManager;
		}

		@Override
		protected UserTransaction locateUserTransaction() {
			return transaction;
		}
	}
}
