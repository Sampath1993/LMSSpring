/**
 * 
 */
package com.gcit.lms;

import java.sql.SQLException;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Borrower;
import com.gcit.lms.service.AdminService;

/**
 * @author admin
 *
 */
@Controller
public class BorrowerController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/borrower", method = RequestMethod.GET)
	public String gotoBorrower(Locale locale, Model model) {
		return "borrower";
	}
	
	@RequestMapping(value = "/addborrower", method = RequestMethod.GET)
	public String gotoAddBorrower(Locale locale, Model model) {
		model.addAttribute("borrower", new Borrower());
		return "addauthor";
	}
	
	@RequestMapping(value = "/viewborrower", method = RequestMethod.GET)
	public String gotoViewBorrower(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getBorrowersCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("borrower", adminService.readBorrowers(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewborrower";
	}
	
	@RequestMapping(value = "/editborrower", method = RequestMethod.GET)
	public String gotoEditBorrower(Locale locale, Model model, @RequestParam(value = "borrowerId", required = false) Integer borrowerId) {
		try {
			model.addAttribute("borrower", adminService.readBorrowerByPK(borrowerId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editborrower";
	}
	
	@RequestMapping(value = "/editborrowerdone", method = RequestMethod.POST)
	public String editAuthor(@Validated @ModelAttribute("borrower") Borrower borrower, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editborrower";
	}
	
	@RequestMapping(value = "/addborrowerdone", method = RequestMethod.POST)
	public String addAuthor(@Validated @ModelAttribute("borrower") Borrower borrower, BindingResult result, Locale locale, Model model) {
		try {
			//System.out.println(author.getAuthorName());
			adminService.saveBorrower(borrower);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewborrower";
	}
	
	@RequestMapping(value = "/deleteBorrower", method = RequestMethod.GET)
	public String DeleteAuthor(Locale locale, Model model, @RequestParam(value = "borrowerId") Integer borrowerId) {
		try {
			adminService.deleteBorrower(adminService.readBorrowerByPK(borrowerId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewborrower";
	}
	
}
