package org.tech.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@RequestMapping(value="/home")
	public ModelAndView showHome(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}
	
	@RequestMapping(value="/index")
	public ModelAndView showIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("index");
	    return mav;
	}
}
