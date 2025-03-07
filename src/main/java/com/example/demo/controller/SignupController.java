package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;

/*
 * ログイン画面 Controller
 *
 */

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.service.SignupService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignupController {
	

    //  ログイン画面サービス
	
	private final SignupService service;

    private final MessageSource messageSource;

	// private final MessageSource messageSource;

	
    @GetMapping("/signup")
    public String view(Model model, SignupForm form) {
        return "signup";
    }
    
    @PostMapping("/signup")
    public void login(Model model, SignupForm form)
    {
		var userInfoOpt = service.registerUserInfo(form);
        var message = AppUtil.getMessages(messageSource, judgeMessageKey(userInfoOpt));
        model.addAttribute("message", message);
    }

    private String judgeMessageKey(Optional<UserInfo> userInfoOpt) 
    {
        if (userInfoOpt.isEmpty()) {
            return MessageConst.SIGNUP_USER_EXISTED;
        } else {
            return MessageConst.SIGNUP_RESIST_SUCCEED;
        }
    }
}
