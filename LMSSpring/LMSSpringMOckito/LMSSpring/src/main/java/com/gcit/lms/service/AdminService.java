package com.gcit.lms.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

public class AdminService {

	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;

	@Transactional
	public Integer saveAuthor(Author author) throws SQLException {
		if (author.getAuthorId() != null) {
			adao.updateAuthor(author);
		} else {
			return adao.saveAuthorWithID(author);
		}
		
		return null;
	}

	@Transactional
	public void deleteAuthor(Author author) throws SQLException {
		adao.deleteAuthor(author);
	}

	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException {
		List<Author> authors = adao.readAuthors(searchString, pageNo);
		for(Author a: authors){
			a.setBooks(bdao.readAllBooksByAuthor(a));
		}
		return authors;
	}
	
	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		List<Book> books = bdao.readAllBooks();
		for(Book b: books){
			b.setAuthors(adao.readAuthorsByBook(b));
			//b.setGenres
			//b.setPublisher
		}
		return books;
	}

	public List<Book> readBooks() throws SQLException {
		return bdao.readAllBooks();
	}

	public Author readAuthorByPK(Integer authorId) throws SQLException {
		return adao.readAuthorByPK(authorId);
	}

	public Integer getAuthorsCount() throws SQLException {
		return adao.getAuthorsCount();
	}

	public Integer getTestCount(){
		return new Integer(100);
	}
	
}
