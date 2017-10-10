package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.entity.Publisher;

@Transactional
public class AdminService {

	@Autowired
	AuthorDAO adao;

	@Autowired
	BookDAO bdao;

	@Autowired
	GenreDAO gdao;

	@Autowired
	PublisherDAO pdao;

	@Autowired
	BorrowerDAO brdao;

	@Autowired
	Library_BranchDAO lbdao;

	@Autowired
	BookCopiesDAO bcdao;

	@Autowired
	BookLoansDAO bldao;

	public void saveAuthor(Author author) throws SQLException {
		if (author.getAuthorId() != null) {
			adao.updateAuthor(author);
			adao.deleteBookAuthor(author);
			adao.saveBookAuthor(author);
		} else {
			int id = adao.saveAuthorWithID(author);
			author.setAuthorId(id);
			adao.saveBookAuthor(author);
		}
	}

	public void deleteAuthor(Author author) throws SQLException {
		adao.deleteAuthor(author);
	}

	public List<Author> readAuthors(String searchString) throws SQLException {
		return adao.readAuthors(searchString);
	}

	public List<Author> readAuthors(String searchString, Integer pageNo) throws SQLException {
		List<Author> authors = adao.readAuthors(searchString, pageNo);
		for (Author a : authors) {
			a.setBooks(bdao.readBooksByAuthor(a));
		}
		return authors;
	}

	public List<Genre> readGenres(String searchString, Integer pageNo) throws SQLException {
		return gdao.readGenres(searchString, pageNo);
	}

	public List<Publisher> readPublishers(String searchString, Integer pageNo) throws SQLException {
		return pdao.readPublishers(searchString, pageNo);
	}

	public List<Borrower> readBorrowers(String searchString, Integer pageNo) throws SQLException {
		return brdao.readBorrowers(searchString, pageNo);
	}

	public List<Library_Branch> readLibraryBranch(String searchString, Integer pageNo) throws SQLException {
		return lbdao.readBranches(searchString, pageNo);
	}

	public List<Book> readBooks(String searchString, Integer pageNo) throws SQLException {
		List<Book> books = bdao.readBooks(searchString, pageNo);
		for (Book b : books) {
			b.setAuthors(adao.readAuthorsByBook(b.getBookId()));
			b.setGenres(gdao.readGenreByBook(b.getBookId()));
			b.setPublisher(pdao.readPublisherByPK(b.getPublisher().getPublisherId()));
		}
		return books;
	}

	public List<BookLoans> readAllLoans(String searchString, Integer pageNo) throws SQLException {
		return bldao.readAllLoans(searchString, pageNo);
	}

	public Author readAuthorByPK(Integer authorId) throws SQLException {
		Author author = adao.readAuthorByPK(authorId);
		author.setBooks(bdao.readBooksByAuthor(authorId));
		return author;
	}

	public Genre readGenreByPK(Integer genreId) throws SQLException {
		return gdao.readGenreByPK(genreId);
	}

	public Publisher readPublisherByPK(Integer publisherId) throws SQLException {
		return pdao.readPublisherByPK(publisherId);
	}

	public Borrower readBorrowerByPK(Integer borrowerId) throws SQLException {
		return brdao.readBorrowerByPK(borrowerId);
	}

	public Library_Branch readBranchByPK(Integer branchId) throws SQLException {
		return lbdao.readBranchByPK(branchId);
	}

	public Book readBookByPK(Integer bookId) throws SQLException {
		Book book = bdao.readBookByPK(bookId);
		book.setAuthors(adao.readAuthorsByBook(bookId));
		book.setGenres(gdao.readGenreByBook(bookId));
		if (book.getPublisher().getPublisherId() != null) {
			book.setPublisher(pdao.readPublisher(book.getPublisher().getPublisherId()));
		}
		return book;
	}

	public Integer getAuthorsCount() throws SQLException {
		return adao.getAuthorsCount();
	}

	public Integer getGenresCount() throws SQLException {
		return gdao.getGenresCount();
	}

	public Integer getPublishersCount() throws SQLException {
		return pdao.getPublishersCount();
	}

	public Integer getBorrowersCount() throws SQLException {
		return brdao.getBorrowersCount();
	}

	public Integer getBranchesCount() throws SQLException {
		return lbdao.getBranchesCount();
	}

	public Integer getBooksCount() throws SQLException {
		return bdao.getBooksCount();
	}

	public Integer getBookLoansCount() throws SQLException {
		return bldao.getBookLoansCount();
	}

	public Integer saveBook(Book book) throws SQLException {
		int id = 0;
		if (book.getBookId() != null) {
			bdao.updateBook(book);
			bdao.deleteBookAuthor(book);
			bdao.saveBookAuthor(book);
		} else {
			id = bdao.saveBookID(book);
			book.setBookId(id);
			bdao.saveBookAuthor(book);
		}
		return id;
	}

	public void deleteBook(Book book) throws SQLException {
		bdao.deleteBook(book);
	}

	public List<Book> readBooks(String searchString) throws SQLException {
		return bdao.readBooksByTitle(searchString);
	}

	public List<Book> readBooks() throws SQLException {
		return bdao.readAllBooks();
	}

	public List<Author> readAuthors() throws SQLException {
		return adao.readAuthors(null);
	}

	public List<Publisher> readPublishers() throws SQLException {
		return pdao.readPublishers(null);
	}

	public List<Genre> readGenres() throws SQLException {
		return gdao.readGenres(null);
	}

	public List<Library_Branch> readBranches() throws SQLException {
		return lbdao.readBranch(null);
	}

	public Integer saveLibraryBranch(Library_Branch libraryBranch) throws SQLException {
		Integer id = 0;
		if (libraryBranch.getBranchId() != null) {
			lbdao.updateBranch(libraryBranch);
		} else {
			id = lbdao.saveLibraryBranchWithID(libraryBranch);
		}
		return id;
	}

	public BookCopies getBookCopies(Integer branchId, Integer bookId) throws SQLException {
		BookCopies temp = bcdao.getBookCopies(bookId, branchId);
		return temp;
	}

	public void saveBookCopies(BookCopies bookCopies) throws SQLException {
		bcdao.saveBC(bookCopies);
	}

	public void deleteLibraryBranch(Library_Branch libraryBranch) throws SQLException {
		lbdao.deleteLibraryBranch(libraryBranch);
	}

	public List<Library_Branch> readLibraryBranch(String searchString) throws SQLException {
		return lbdao.readBranch(searchString);
	}

	public void savePublisher(Publisher publisher) throws SQLException {
		if (publisher.getPublisherId() != null) {
			pdao.updatePublisher(publisher);
		} else {
			pdao.savePublisher(publisher);
		}
	}

	public void deletePublisher(Publisher publisher) throws SQLException {
		pdao.deletePublisher(publisher);
	}

	public List<Publisher> readPublisher(String searchString) throws SQLException {
		return pdao.readPublishers(searchString);
	}

	public void saveGenre(Genre genre) throws SQLException {
		if (genre.getGenreId() != null) {
			gdao.updateGenre(genre);
			gdao.deleteBookGenre(genre);
			gdao.saveBookGenre(genre);
		} else {
			int id = gdao.saveGenreWithID(genre);
			genre.setGenreId(id);
			gdao.saveBookGenre(genre);
		}
	}

	public void deleteGenre(Genre genre) throws SQLException {
		gdao.deleteGenre(genre);
	}

	public List<Genre> readGenre(String searchString) throws SQLException {
		return gdao.readGenres(searchString);
	}

	public void saveBorrower(Borrower borrower) throws SQLException {
		if (borrower.getCardNo() != null) {
			brdao.updateBorrower(borrower);
		} else {
			brdao.saveBorrower(borrower);
		}
	}

	public void deleteBorrower(Borrower borrower) throws SQLException {
		brdao.deleteBorrower(borrower);
	}

	public List<Borrower> readBorrower(String searchString) throws SQLException {
		return brdao.readBorrowers(searchString);
	}

	public void saveBookLoans(BookLoans bookLoans) throws SQLException {
		if (bookLoans.getDateIn() != null) {
			bldao.saveBookLoanComplete(bookLoans);
		} else {
			bldao.saveBookLoan(bookLoans);
		}
	}

	public void deleteBookLoans(BookLoans bookLoans) throws SQLException {
		bldao.deleteBookLoan(bookLoans);
	}

	public void overrideBookLoanDueDate(BookLoans bookLoans) throws SQLException {
		bldao.overrideDueDate(bookLoans);
	}

	public void updateBookLoanDueDate(BookLoans bookLoans) throws SQLException {
		bldao.updateBookLoanDueDate(bookLoans);
	}

}
