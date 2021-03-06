package com.gcit.lms.test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.service.AdminService;

@Configuration
public class LMSConfig {

	public String driver = "com.mysql.cj.jdbc.Driver";
	public String url = "jdbc:mysql://localhost/library?useSSL=false";
	public String user = "root";
	public String password = "root";
	
	@Bean
//	@Scope(value="prototype")
	public BasicDataSource dataSource(){
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		
		return ds;
	}
	
	
	@Bean
//	@Qualifier(value="oracleTemplate")
	public JdbcTemplate template(){
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public AuthorDAO adao(){
		return new AuthorDAO();
	}
	
	@Bean
	public BookDAO bdao(){
		return new BookDAO();
	}
	
	@Bean
	public PlatformTransactionManager txManager(){
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public AdminService adminService(){
		return new AdminService();
	}
}
