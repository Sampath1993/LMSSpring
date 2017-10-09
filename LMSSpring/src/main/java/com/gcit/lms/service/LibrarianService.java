package com.gcit.lms.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcit.lms.dao.BookCopiesDAO;
import com.gcit.lms.dao.BookDAO;
import com.gcit.lms.dao.Library_BranchDAO;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Library_Branch;

public class LibrarianService {

	@Autowired
	BookDAO bdao;

	@Autowired
	Library_BranchDAO lbdao;

	@Autowired
	BookCopiesDAO bcdao;
	
	public List<Library_Branch> readBranch(String searchString) throws SQLException{
			return lbdao.readBranch(searchString);
	}
	
	public List<Book> readBooks(Library_Branch libraryBranch, Integer pageNo) throws SQLException {
			return bdao.readBooksByBranch(libraryBranch, pageNo);
	}
	
	public Integer getBooksBranchCount(Integer branchId) throws SQLException{
			return bcdao.getCheckOutBooksCount(branchId);
	}
	
	public Book readBookByPK(Integer bookId) throws SQLException{
			return bdao.readBookByPK(bookId);
	}
	
	public Integer getBooksBrCount(Integer bookId, Integer branchId) throws SQLException{
			return bcdao.countBookCopies(bcdao.getBookCopies(bookId, branchId));
	}
	
	public void updateBranch(Library_Branch libraryBranch) throws SQLException{
			lbdao.updateBranch(libraryBranch);
	}
	
	public Library_Branch readBranchByPK(Integer branchId) throws SQLException{
			return lbdao.readBranchByPK(branchId);
	}
	
	public void updateNoOfCopies(BookCopies bookCopies) throws SQLException{
			bcdao.updateBookCopies(bookCopies);
	}

	public BookCopies showNoOfCopies(BookCopies bookCopies) throws SQLException {
			return bcdao.showBookCopies(bookCopies);
	}
	
}
