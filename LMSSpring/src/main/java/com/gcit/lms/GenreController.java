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

import com.gcit.lms.entity.Genre;
import com.gcit.lms.service.AdminService;

public class GenreController {
	
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	AdminService adminService;


	@RequestMapping(value = "/genre", method = RequestMethod.GET)
	public String gotoGenre(Locale locale, Model model) {
		return "genre";
	}
	
	@RequestMapping(value = "/addgenre", method = RequestMethod.GET)
	public String gotoAddGenre(Locale locale, Model model) {
		model.addAttribute("genre", new Genre());
		try {
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "addgenre";
	}
	
	@RequestMapping(value = "/viewgenres", method = RequestMethod.GET)
	public String gotoViewGenre(Locale locale, Model model, @RequestParam(value = "pageNo", required = false) Integer pageNo) {
		try {
			Integer totalCount = adminService.getGenresCount();
			int numOfPages = 0;
			if (totalCount % 10 > 0) {
				numOfPages = totalCount / 10 + 1;
			} else {
				numOfPages = totalCount / 10;
			}
			if(pageNo == null) {
				pageNo = 1;
			}
			model.addAttribute("genres", adminService.readGenres(null, pageNo));
			model.addAttribute("totalCount", totalCount);
			model.addAttribute("numOfPages", numOfPages);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "viewgenres";
	}
	
	@RequestMapping(value = "/editgenre", method = RequestMethod.GET)
	public String gotoEditGenre(Locale locale, Model model, @RequestParam(value = "genreId", required = false) Integer genreId) {
		try {
			model.addAttribute("genre", adminService.readGenreByPK(genreId));
			model.addAttribute("books", adminService.readBooks());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "editgenre";
	}
	
	@RequestMapping(value = "/editgenredone", method = RequestMethod.POST)
	public String editGenre(@Validated @ModelAttribute("genre") Genre genre, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewgenres";
	}
	
	@RequestMapping(value = "/addgenredone", method = RequestMethod.POST)
	public String addGenre(@Validated @ModelAttribute("genre") Genre genre, BindingResult result, Locale locale, Model model) {
		try {
			adminService.saveGenre(genre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewgenres";
	}
	
	@RequestMapping(value = "/deleteGenre", method = RequestMethod.GET)
	public String DeleteGenre(Locale locale, Model model, @RequestParam(value = "genreId") Integer genreId) {
		try {
			adminService.deleteGenre(adminService.readGenreByPK(genreId));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/viewgenres";
	}
	
}
