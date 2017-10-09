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
import com.gcit.lms.entity.Publisher;
import com.gcit.lms.service.AdminService;

public class PublisherController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/publisher", method = RequestMethod.GET)
	public String gotoPublisher(Locale locale, Model model) {
		return "publisher";
	}
	
	@RequestMapping(value = "/addpublisher", method = RequestMethod.GET)
	public String gotoAddPublisher(Locale locale, Model model) {
		model.addAttribute("publisher", new Publisher());
		return "addpublisher";
	}
	
	@RequestMapping(value = "/viewpublishers", method = RequestMethod.GET)
	public String gotoViewPublisher(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getPublishersCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("publishers", adminService.readPublishers(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewpublisher";
	}
	
	@RequestMapping(value = "/editpublisher", method = RequestMethod.GET)
	public String gotoEditPublisher(Locale locale, Model model, @RequestParam(value = "publisherId", required = false) Integer pubId) {
		try {
			model.addAttribute("publishers", adminService.readPublisherByPK(pubId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editpublisher";
	}
	
	@RequestMapping(value = "/editpublisherdone", method = RequestMethod.POST)
	public String editPublisher(@Validated @ModelAttribute("publisher") Publisher publisher, BindingResult result, Locale locale, Model model) {
		try {
			adminService.savePublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewpublishers";
	}
	
	@RequestMapping(value = "/addpublisherdone", method = RequestMethod.POST)
	public String addPublisher(@Validated @ModelAttribute("publisher") Publisher publisher, BindingResult result, Locale locale, Model model) {
		try {
			adminService.savePublisher(publisher);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewpublishers";
	}
	
	@RequestMapping(value = "/deletePublishers", method = RequestMethod.GET)
	public String DeletePublisher(Locale locale, Model model, @RequestParam(value = "pubId") Integer pubId) {
		try {
			adminService.deletePublisher(adminService.readPublisherByPK(pubId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewpublishers";
	}
	
}
