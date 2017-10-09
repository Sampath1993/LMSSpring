package com.gcit.lms;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.service.AdminService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	AdminService adminService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String gotoAdmin(Locale locale, Model model) {
		return "admin";
	}

	@RequestMapping(value = "/override", method = RequestMethod.GET)
	public String gotoOverride(Locale locale, Model model) {
		return "override";
	}

	@RequestMapping(value = "/overridedone", method = RequestMethod.GET)
	public String Override(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo, @RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "bookId") Integer bookId) {
		BookLoans bookLoans = new BookLoans();
		bookLoans.setBookId(bookId);
		bookLoans.setBranchId(branchId);
		bookLoans.setCardNo(cardNo);
		try {
			adminService.overrideBookLoanDueDate(bookLoans);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "override";
	}
}
