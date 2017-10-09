package com.gcit.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

@SuppressWarnings("rawtypes")
public class BookDAO extends BaseDAO implements ResultSetExtractor<List<Book>>{

	public void saveBook(Book book) throws SQLException {
		template.update("INSERT INTO tbl_book (bookName) VALUES (?)", new Object[] { book.getTitle() });
	}
	
	public void saveBookAuthor(Book book) throws SQLException {
		for(Author a: book.getAuthors()){
			template.update("INSERT INTO tbl_book_authors VALUES (?, ?)", new Object[] { book.getBookId(), a.getAuthorId()});
		}
	}
	
	public Integer saveBookID(Book book) throws SQLException {
		KeyHolder holder = new GeneratedKeyHolder();
		final String sql = "INSERT INTO tbl_book (bookName) VALUES (?)";
		template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, book.getTitle());
				return ps;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	public void updateBook(Book book) throws SQLException {
		template.update("UPDATE tbl_book SET bookName = ? WHERE bookId = ?", new Object[] { book.getTitle(), book.getBookId() });
	}

	public void deleteBook(Book book) throws SQLException {
		template.update("DELETE FROM tbl_book WHERE bookId = ?", new Object[] { book.getBookId() });
	}

	public List<Book> readAllBooks() throws SQLException {
		return template.query("SELECT * FROM tbl_book", this);
	}
	
	public List<Book> readAllBooksByAuthor(Author author) throws SQLException {
		return template.query("SELECT * FROM tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[]{author.getAuthorId()}, this);
	}
	
	public List<Book> readBooksByTitle(String bookTitle) throws SQLException {
		bookTitle = "%"+bookTitle+"%";
		return template.query("SELECT * FROM tbl_book WHERE title like ?", new Object[]{bookTitle}, this);
	}

	@Override
	public List extractData(ResultSet rs) throws SQLException {
		List<Book> books = new ArrayList<>();
		while (rs.next()) {
			Book b = new Book();
			b.setBookId(rs.getInt("bookId"));
			b.setTitle(rs.getString("title"));
			Publisher pub = new Publisher();
			pub.setPublisherId(rs.getInt("pubId"));
			b.setPublisher(pub);
			books.add(b);
		}
		return books;
	}
}
