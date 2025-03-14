package com.example.demo.service;

import java.util.Optional;

import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserInfoRepository repository;

    private final Mapper mapper;

	private final PasswordEncoder passwordEncoder;

    public Optional<UserInfo> registerUserInfo(SignupForm form) {
        var userInfoExistedOpt = repository.findById(form.getLoginId());

        if (userInfoExistedOpt.isPresent()) {
            return Optional.empty();
        }

        var userInfo = mapper.map(form, UserInfo.class);
        userInfo.setPassword(passwordEncoder.encode(form.getPassword()));
        
        return Optional.of(repository.save(userInfo));
    }
}