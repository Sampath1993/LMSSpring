package com.gcit.lms;


import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		return "welcome";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String gotoAdmin(Locale locale, Model model) {
		return "admin";
	}
	
	
	@RequestMapping(value = "/book", method = RequestMethod.GET)
	public String gotoBook(Locale locale, Model model) {
		return "book";
	}
	
	@RequestMapping(value = "/addbook", method = RequestMethod.GET)
	public String gotoAddBook(Locale locale, Model model) {
		return "addbook";
	}
	
	@RequestMapping(value = "/viewbooks", method = RequestMethod.GET)
	public String gotoViewBook(Locale locale, Model model) {
		return "viewbooks";
	}
	
	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	public String gotoGenre(Locale locale, Model model) {
		return "genre";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.GET)
	public String gotoAddGenre(Locale locale, Model model) {
		return "addgenre";
	}
	
	@RequestMapping(value = "/viewgenres", method = RequestMethod.GET)
	public String gotoViewGenre(Locale locale, Model model) {
		return "viewgenres";
	}
	
	@RequestMapping(value = "/branch", method = RequestMethod.GET)
	public String gotoBranch(Locale locale, Model model) {
		return "branch";
	}
	
	@RequestMapping(value = "/addbranch", method = RequestMethod.GET)
	public String gotoAddBranch(Locale locale, Model model) {
		return "addgenre";
	}
	
	@RequestMapping(value = "/viewbranch", method = RequestMethod.GET)
	public String gotoViewBranch(Locale locale, Model model) {
		return "viewbranch";
	}
	
	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String gotoBorrower(Locale locale, Model model) {
		return "borrower";
	}
	
	@RequestMapping(value = "/addborrower", method = RequestMethod.GET)
	public String gotoAddBorrower(Locale locale, Model model) {
		return "addborrower";
	}
	
	@RequestMapping(value = "/viewborrower", method = RequestMethod.GET)
	public String gotoViewBorrower(Locale locale, Model model) {
		return "viewborrower";
	}
	
	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String gotoPublisher(Locale locale, Model model) {
		return "publisher";
	}
	
	@RequestMapping(value = "/addpublisher", method = RequestMethod.GET)
	public String gotoAddPublisher(Locale locale, Model model) {
		return "addpublisher";
	}
	
	@RequestMapping(value = "/viewpublisher", method = RequestMethod.GET)
	public String gotoViewPublisher(Locale locale, Model model) {
		return "viewpublisher";
	}
}
