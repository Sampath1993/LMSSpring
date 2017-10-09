package com.gcit.lms;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.service.AdminService;

public class BookController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String gotoBook(Locale locale, Model model) {
		return "book";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String gotoAddBook(Locale locale, Model model) {
		model.addAttribute("book", new Book());
		try {
			model.addAttribute("authors", adminService.readAuthors());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addbook";
	}
	
	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
	public String gotoViewBooks(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getBooksCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("books", adminService.readBooks(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewbooks";
	}
	
	@RequestMapping(value = "/editbook", method = RequestMethod.GET)
	public String gotoEditBook(Locale locale, Model model, @RequestParam(value = "bookId", required = false) Integer bookId) {
		try {
			model.addAttribute("book", adminService.readBookByPK(bookId));
			model.addAttribute("authors", adminService.readAuthors());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editbook";
	}
	
	@RequestMapping(value = "/editbookdone", method = RequestMethod.POST)
	public String editBook(@Validated @ModelAttribute("book") Book book, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbooks";
	}
	
	@RequestMapping(value = "/addbookdone", method = RequestMethod.POST)
	public String addBook(@Validated @ModelAttribute("book") Book book, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveBook(book);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbooks";
	}
	
	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET)
	public String deleteBook(Locale locale, Model model, @RequestParam(value = "bookId") Integer bookId) {
		try {
			adminService.deleteBook(adminService.readBookByPK(bookId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbooks";
	}
	
}
