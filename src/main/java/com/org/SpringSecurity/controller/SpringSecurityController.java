package com.org.SpringSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpringSecurityController {
	String message = "Spring Security";

	@RequestMapping("/sample")
	public ModelAndView showMessage(
			@RequestParam(value="name", required=false, defaultValue="Sample") String name) {
		System.out.println("in controller");

		ModelAndView mv = new ModelAndView("samplepage");
		mv.addObject("message",message);
		mv.addObject("name",name);
		return mv;
	}

}
