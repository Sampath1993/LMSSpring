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
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.AdminService;
import com.gcit.lms.service.BorrowerService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class BorController {

	private static final Logger logger = LoggerFactory.getLogger(BorController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Autowired
	BorrowerService borrowerService;

	@RequestMapping(value = "/bor", method = RequestMethod.GET)
	public String gotoBorrower(Locale locale, Model model) {
		return "bor";
	}
	
	@RequestMapping(value = "/validateCard", method = RequestMethod.GET)
	public String validateCard(Locale locale, Model model, @RequestParam("cardNo") Integer cardNo) {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		try {
			if(borrowerService.validateCard(borrower))
			{
				return "borrowerbranch";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "bor";
	}
	
	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String gotoCheckOut(Locale locale, Model model) {
		
		return "checkoutbooks";
	}
}
