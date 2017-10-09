package com.gcit.lms.test.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.test.LMSConfig;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {LMSConfig.class})
public class TestAuthorServicesMock {
	
	@InjectMocks
	AdminService adminService = new AdminService();
	
	@Mock
	AuthorDAO adao;
	
	@Mock
	BookDAO bdao;
	
	@Before
	public void testMockCreation(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAuthorAdd(){
		Author author = new Author();
		author.setAuthorName("From Junit");
		try {
			when(adao.saveAuthorWithID(author)).thenReturn(new Integer(3));
			Integer authorId = adminService.saveAuthor(author);
			assertNotNull(authorId);
			assertEquals(new Integer(2), authorId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(adminService);
	}
	
	

}
