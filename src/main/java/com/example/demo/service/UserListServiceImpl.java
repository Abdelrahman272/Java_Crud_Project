package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.UserListInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.github.dozermapper.core.Mapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserListServiceImpl implements UserListService {

    private final UserInfoRepository repository;
    private final Mapper mapper;

    @Override
    public List<UserListInfo> editUserList() {
        return toUserListInfos(repository.findAll());
    }

    private List<UserListInfo> toUserListInfos(List<UserInfo> userInfos) {
        var userListInfos = new ArrayList<UserListInfo>();
        for (UserInfo userInfo : userInfos) {
            var userListInfo = mapper.map(userInfo, UserListInfo.class);
            userListInfo.setStatus(userInfo.getStatus().getDisplayValue());
            userListInfo.setAuthority(userInfo.getAuthority().getDisplayValue());
            userListInfos.add(userListInfo);
        }
        return userListInfos;
    }
}