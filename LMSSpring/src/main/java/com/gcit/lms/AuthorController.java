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
import com.gcit.lms.service.AdminService;

/**
 * @author admin
 *
 */
@Controller
public class AuthorController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/author", method = RequestMethod.GET)
	public String gotAuthor(Locale locale, Model model) {
		return "author";
	}
	
	@RequestMapping(value = "/addauthor", method = RequestMethod.GET)
	public String gotoAddAuthor(Locale locale, Model model) {
		model.addAttribute("author", new Author());
		try {
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addauthor";
	}
	
	@RequestMapping(value = "/viewauthors", method = RequestMethod.GET)
	public String gotoViewAuthor(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getAuthorsCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("authors", adminService.readAuthors(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewauthors";
	}
	
	@RequestMapping(value = "/editauthor", method = RequestMethod.GET)
	public String gotoEditAuthor(Locale locale, Model model, @RequestParam(value = "authorId", required = false) Integer authorId) {
		try {
			model.addAttribute("author", adminService.readAuthorByPK(authorId));
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editauthor";
	}
	
	@RequestMapping(value = "/editauthordone", method = RequestMethod.GET)
	public String editAuthor(Locale locale, Model model, @RequestParam(value = "authorId", required = false) Integer authorId) {
		try {
			model.addAttribute("author", adminService.readAuthorByPK(authorId));
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editauthor";
	}
	
	@RequestMapping(value = "/addauthordone", method = RequestMethod.POST)
	public String addAuthor(@Validated @ModelAttribute("author") Author author, BindingResult result, Locale locale, Model model) {
		try {
			System.out.println(author.getAuthorName());
			adminService.saveAuthor(author);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewauthors";
	}
	
	@RequestMapping(value = "/deleteAuthor", method = RequestMethod.GET)
	public String DeleteAuthor(Locale locale, Model model, @RequestParam(value = "authorId") Integer authorId) {
		try {
			adminService.deleteAuthor(adminService.readAuthorByPK(authorId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewauthors";
	}
	
}
