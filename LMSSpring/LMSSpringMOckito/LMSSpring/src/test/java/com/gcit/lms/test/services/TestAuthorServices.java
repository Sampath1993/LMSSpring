package com.gcit.lms.test.services;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.test.LMSConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {LMSConfig.class})
public class TestAuthorServices {
	@Autowired
	AdminService adminService;

	@Test
	public void testAuthorAddService(){
		assertNotNull(adminService);
	}
	
	@Test
	public void testAuthorAdd(){
		Author author = new Author();
		author.setAuthorName("From Junit");
		try {
			adminService.saveAuthor(author);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(adminService);
	}

}
