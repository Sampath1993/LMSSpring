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

import com.gcit.lms.entity.Borrower;
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
	public String validateCard(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo) {
		Borrower borrower = new Borrower();
		borrower.setCardNo(cardNo);
		try {
			if (borrowerService.validateCard(borrower)) {
				return "borrowerbranch";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "bor";
	}

	@RequestMapping(value = "/checkOut", method = RequestMethod.GET)
	public String gotoCheckOut(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo,
			@RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "bookId") Integer bookId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		Integer pageNumber = 1;
		if (pageNo != null) {
			pageNumber = pageNo;
		}
		try {
			model.addAttribute("books",
					borrowerService.readBooksCheckOut(borrowerService.readBranchByPK(branchId), pageNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkoutbooks";
	}

	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String gotoReturn(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo,
			@RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "bookId") Integer bookId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		Integer pageNumber = 1;
		if (pageNo != null) {
			pageNumber = pageNo;
		}
		try {
			model.addAttribute("books", borrowerService.readBooksReturn(borrowerService.readBranchByPK(branchId),
					borrowerService.readBorrowerByPK(cardNo), pageNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "returnbooks";
	}
	
	@RequestMapping(value = "/checkOutBook", method = RequestMethod.GET)
	public String checkOut(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo,
			@RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "bookId") Integer bookId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		Integer pageNumber = 1;
		if (pageNo != null) {
			pageNumber = pageNo;
		}
		try {
			borrowerService.checkOutBook(borrowerService.getBookLoans(branchId, bookId, cardNo), borrowerService.getBookCopies(branchId, bookId));
			model.addAttribute("books",
					borrowerService.readBooksCheckOut(borrowerService.readBranchByPK(branchId), pageNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "checkoutbooks";
	}
	
	@RequestMapping(value = "/return", method = RequestMethod.GET)
	public String returnBook(Locale locale, Model model, @RequestParam(value = "cardNo") Integer cardNo,
			@RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "bookId") Integer bookId,
			@RequestParam(value = "pageNo", required = false) Integer pageNo) {
		model.addAttribute("cardNo", cardNo);
		model.addAttribute("branchId", branchId);
		Integer pageNumber = 1;
		if (pageNo != null) {
			pageNumber = pageNo;
		}
		try {
			borrowerService.returnBook(borrowerService.getBookLoans(branchId, bookId, cardNo), borrowerService.getBookCopies(branchId, bookId));
			model.addAttribute("books", borrowerService.readBooksReturn(borrowerService.readBranchByPK(branchId),
					borrowerService.readBorrowerByPK(cardNo), pageNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "returnbooks";
	}
	
}
