package com.example.demo.controller;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UrlConst;

import org.springframework.ui.Model;

@Controller
public class MenuController {

	@GetMapping(UrlConst.MENU)
	public String view(@AuthenticationPrincipal User user, Model model)
	{

		var hasUserManageAuth = user.getAuthorities().stream()
				.allMatch(authority -> authority.getAuthority()
				.equals(AuthorityKind.ITEM_AND_USER_MANAGER.getCode()));
		model.addAttribute("hasUserManageAuth", hasUserManageAuth);
		
		
		return "menu";
	}
}
