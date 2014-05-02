package com.ams.sharedkernel.ports.adapter.rest.springmvc.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
	@RequestMapping("index.action")
	public String loadHomePage(Model model)
	{
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("User Name:" + userDetails.getUsername() + "\n" + "Role:" + userDetails.getAuthorities());

		return "index";
	}

	@RequestMapping(value = "login.action")
	public String loginPage(Model model)
	{
		return "login";
	}
}
