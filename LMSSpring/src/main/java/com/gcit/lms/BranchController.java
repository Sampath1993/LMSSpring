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

import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Library_Branch;
import com.gcit.lms.service.AdminService;

public class BranchController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/branch", method = RequestMethod.GET)
	public String gotoBranch(Locale locale, Model model) {
		return "branch";
	}
	
	@RequestMapping(value = "/addbranch", method = RequestMethod.GET)
	public String gotoAddBranch(Locale locale, Model model) {
		model.addAttribute("branch", new Library_Branch());
		try {
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addbranch";
	}
	
	@RequestMapping(value = "/viewbranches", method = RequestMethod.GET)
	public String gotoViewGenre(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getBranchesCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("branches", adminService.readLibraryBranch(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewbranches";
	}
	
	@RequestMapping(value = "/editbranch", method = RequestMethod.GET)
	public String gotoEditGenre(Locale locale, Model model, @RequestParam(value = "branchId", required = false) Integer branchId) {
		try {
			model.addAttribute("branch", adminService.readBranchByPK(branchId));
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editbranch";
	}
	
	@RequestMapping(value = "/editbranchdone", method = RequestMethod.POST)
	public String editGenre(@Validated @ModelAttribute("branch") Library_Branch branch, @Validated @ModelAttribute("bookCopies") BookCopies bookCopies, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveLibraryBranch(branch);
			adminService.saveBookCopies(bookCopies);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbranches";
	}
	
	@RequestMapping(value = "/addbranchdone", method = RequestMethod.POST)
	public String addGenre(@Validated @ModelAttribute("branch") Library_Branch branch, @Validated @ModelAttribute("bookCopies") BookCopies bookCopies, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveLibraryBranch(branch);
			adminService.saveBookCopies(bookCopies);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbranches";
	}
	
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String DeleteGenre(Locale locale, Model model, @RequestParam(value = "branchId") Integer branchId) {
		try {
			adminService.deleteLibraryBranch(adminService.readBranchByPK(branchId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewbranches";
	}
	
}
