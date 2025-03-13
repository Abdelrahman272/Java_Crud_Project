package com.example.demo.controller;

/*
 * ログイン画面 Controller
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.form.LoginForm;
import com.example.demo.service.LoginService;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.context.MessageSource;
import com.example.demo.util.AppUtil;
import com.example.demo.constant.MessageConst;
import com.example.demo.constant.UrlConst;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginController {
	

	
	//  ログイン画面サービス

	private final LoginService service;

	private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	private final MessageSource messageSource;

	
    @GetMapping(UrlConst.LOGIN)
    public String view(Model model, LoginForm form) {
        return "login";
    }
    
    @PostMapping(UrlConst.LOGIN)
    public String login(Model model, LoginForm form)
    {
    	var userInfo = service.searchUserById(form.getLoginId());
    	
    	var isCorrectUserAuth = userInfo.isPresent() 
		&& passwordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
    	
    	if(isCorrectUserAuth)
    	{
    		return "redirect:/menu";
    	}else {
			var errorMsg = AppUtil.getMessages(messageSource, MessageConst.LOGIN_WRONG_INPUT);
    		model.addAttribute("errorMsg", errorMsg);
    		return "login";
    	}
    	
    }
}
