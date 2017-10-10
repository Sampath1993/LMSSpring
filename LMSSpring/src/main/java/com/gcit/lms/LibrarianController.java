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

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.service.LibrarianService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LibrarianController {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */

	@Autowired
	LibrarianService libService;

	@RequestMapping(value = "/librarian", method = RequestMethod.GET)
	public String gotoBorrower(Locale locale, Model model) {
		return "librarian";
	}

	@RequestMapping(value = "/chooseBranch", method = RequestMethod.GET)
	public String chooseBranch(Locale locale, Model model, @RequestParam(value = "branchId") Integer branchId) {
		model.addAttribute("branchId", branchId);
		return "library";
	}

	@RequestMapping(value = "/updateBranch", method = RequestMethod.GET)
	public String updateBranch(Locale locale, Model model, @RequestParam(value = "branchId") Integer branchId) {
		model.addAttribute("branchId", branchId);
		return "updatebranch";
	}
	
	@RequestMapping(value = "/addCopies", method = RequestMethod.GET)
	public String addCopies(Locale locale, Model model, @RequestParam(value = "branchId") Integer branchId, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		model.addAttribute("branchId", branchId);
		Integer pageNumber = 1;
		if (pageNo != null) {
			pageNumber = pageNo;
		}
		try {
			model.addAttribute("books", libService.readBooks(libService.readBranchByPK(branchId), pageNumber));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "branchbooks";
	}
	
	@RequestMapping(value = "/branchUpdate", method = RequestMethod.GET)
	public String branchUpdate(Locale locale, Model model, @RequestParam(value = "branch") Library_Branch branch) {
		model.addAttribute("branchId", branch.getBranchId());
		try {
			libService.updateBranch(branch);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "library";
	}
	
	@RequestMapping(value = "/copiesAdded", method = RequestMethod.GET)
	public String copiesAdded(Locale locale, Model model, @RequestParam(value = "bookCopies") BookCopies bookCopies) {
		model.addAttribute("branchId", bookCopies.getBranchId());		
		try {
			libService.updateNoOfCopies(bookCopies);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "branchbooks";
	}
}
