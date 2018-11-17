package com.xiangrikui.hulk.web.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class DynamicDataSource
{
	@Autowired
	DruidDataSourceConfig druidDataSourceConfig;
	
	@Bean
	public DataSource dataSource(){
		DataSource dataSource = druidDataSourceConfig.targetDataSource();
		return dataSource;
	}
	@Bean
	public PlatformTransactionManager transactionManager() {
	   return new DataSourceTransactionManager(dataSource());
	}
	
	
}
