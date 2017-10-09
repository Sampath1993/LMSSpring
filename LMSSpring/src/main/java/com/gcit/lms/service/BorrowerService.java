package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.lms.dao.AuthorDAO;
import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.BookLoansDAO;
import com.gcit.lms.dao.BorrowerDAO;
import com.gcit.lms.dao.GenreDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.dao.PublisherDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.entity.Library_Branch;

public class BorrowerService {

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

	public List<Library_Branch> readBranch(String searchString) throws SQLException {
		return lbdao.readBranch(searchString);
	}

	public List<Book> readBooksCheckOut(Library_Branch libraryBranch, Integer pageNo) throws SQLException {
		return bdao.readBooksByBranch(libraryBranch, pageNo);
	}

	public List<Book> readBooksReturn(Library_Branch libraryBranch, Borrower borrower, Integer pageNo)
			throws SQLException {
		return bdao.readBooksByBorrower(libraryBranch, borrower, pageNo);
	}

	public void checkOutBook(BookLoans bookLoans, BookCopies bookCopies) throws SQLException {
		bldao.saveBookLoan(bookLoans);
		bcdao.checkOutBookCopies(bookCopies);
	}

	public void returnBook(BookLoans bookLoans, BookCopies bookCopies) throws SQLException {
		bldao.updateBookLoanDateIn(bookLoans);
		bcdao.returnBookCopies(bookCopies);
	}

	public List<Library_Branch> readBranches(Integer pageNo) throws SQLException {
		return lbdao.readBranches(null, pageNo);
	}

	public List<Library_Branch> readLibraryBranch(String searchString, Integer pageNo) throws SQLException {
		return lbdao.readBranches(searchString, pageNo);
	}

	public boolean validateCard(Borrower borrower) throws SQLException {
		return brdao.validateCard(borrower);
	}

	public Integer getBranchesCount() throws SQLException {
		return lbdao.getBranchesCount();
	}

	public Library_Branch readBranchByPK(Integer branchId) throws SQLException {
		return lbdao.readBranchByPK(branchId);
	}

	public Borrower readBorrowerByPK(Integer borrowerId) throws SQLException {
		return brdao.readBorrowerByPK(borrowerId);
	}

	public Integer getCheckOutBooksCount(Integer branchId) throws SQLException {
		return bcdao.getCheckOutBooksCount(branchId);
	}

	public Integer getLoanedOutBooksCount(Integer bookId, Integer cardNo, Integer branchId) throws SQLException {
		return bldao.getLoanedOutBooksCount(getBookLoans(branchId, bookId, cardNo));
	}

	public BookLoans getBookLoans(Integer branchId, Integer bookId, Integer cardNo) throws SQLException {
		return bldao.getBookLoans(branchId, bookId, cardNo);
	}

	public BookCopies getBookCopies(Integer branchId, Integer bookId) throws SQLException {
		return bcdao.getBookCopies(bookId, branchId);
	}

}
