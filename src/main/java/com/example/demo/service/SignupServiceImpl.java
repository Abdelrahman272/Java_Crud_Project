package com.example.demo.service;

import java.util.Optional;

import com.github.dozermapper.core.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SignupServiceImpl implements SignupService {


    private final UserInfoRepository repository;

    private final Mapper mapper;

	private final PasswordEncoder passwordEncoder;

    

    public Optional<UserInfo> registerUserInfo(SignupForm form) {
        var userInfoExistedOpt = repository.findById(form.getLoginId());

        if (userInfoExistedOpt.isPresent()) {
            return Optional.empty();
        }

        var userInfo = mapper.map(form, UserInfo.class);
        var encodedPassword = passwordEncoder.encode(form.getPassword());
        userInfo.setPassword(encodedPassword);
        userInfo.setAuthority(AuthorityKind.ITEM_WATCHER);
        
        return Optional.of(repository.save(userInfo));
    }
}
