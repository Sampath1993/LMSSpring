package com.gcit.lms;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gcit.lms.entity.Author;
import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AdminService adminService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String gotoHome(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		return "welcome";
	}
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String gotoAdmin(Locale locale, Model model) {
		return "admin";
	}
	
	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String gotoAuthor(Locale locale, Model model) {
		return "author";
	}
	
	@RequestMapping(value = "/addAuthor", method = RequestMethod.GET)
	public String gotoAddAuthor(Locale locale, Model model) {
		return "addauthor";
	}
	
	@RequestMapping(value = "/viewAuthors", method = RequestMethod.GET)
	public String gotoViewAuthor(Locale locale, Model model) {
		try {
			Integer totalCount = adminService.getAuthorsCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			model.addAttribute("authors", adminService.readAuthors(null, 1));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewauthors";
	}
	
}
