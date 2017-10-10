package com.gcit.lms;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;
import com.gcit.lms.service.LibrarianService;

@Configuration
public class LMSConfig {
	
	public String driver = "com.mysql.cj.jdbc.Driver";
	public String url = "jdbc:mysql://localhost/library?useSSL=false";
	public String user = "root";
	public String password = "root";
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(user);
		ds.setPassword(password);
		return ds;
	}
	
	@Bean
//	@Qualifier(value = "template")
	public JdbcTemplate template() {
		return new JdbcTemplate(dataSource());
	}
	
	@Bean
	public AuthorDAO adao() {
		return new AuthorDAO();
	}

	@Bean
	public BookDAO bdao() {
		return new BookDAO();
	}
	
	@Bean
	public GenreDAO gdao() {
		return new GenreDAO();
	}
	
	@Bean
	public Library_BranchDAO lbdao() {
		return new Library_BranchDAO();
	}
	
	@Bean
	public PublisherDAO pdao() {
		return new PublisherDAO();
	}
	
	@Bean
	public BorrowerDAO brdao() {
		return new BorrowerDAO();
	}
	
	@Bean
	public BookCopiesDAO bcdao() {
		return new BookCopiesDAO();
	}
	
	@Bean
	public BookLoansDAO bldao() {
		return new BookLoansDAO();
	}
	
	@Bean
	public LibrarianService librarianService() {
		return new LibrarianService();
	}
	
	@Bean
	public BorrowerService borrowerService() {
		return new BorrowerService();
	}
	
	@Bean
	public AdminService adminService() {
		return new AdminService();
	}
	
	@Bean
	public PlatformTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
}
