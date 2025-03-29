package com.example.demo.entity;

import java.time.LocalDateTime;

import com.example.demo.constant.AuthorityKind;
import com.example.demo.constant.UserStatusKind;
import com.example.demo.entity.converter.UserAuthorityConverter;
import com.example.demo.entity.converter.UserStatusConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name="user_info")
@Data
@AllArgsConstructor
public class UserInfo {

	@Id
	@Column(name="login_id")
	private String loginId;
	
	private String password;

	@Column(name="login_failure_count")
	private int loginFailureCount = 0 ; 

	@Column(name="account_locked_time")
	private LocalDateTime accountLockedTime;

	@Column(name="is_disabled")
	@Convert(converter = UserStatusConverter.class)
	private UserStatusKind status;

	@Convert(converter = UserAuthorityConverter.class)
	private AuthorityKind authority;

	public UserInfo() {
	}


	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(loginId, password, ++loginFailureCount, accountLockedTime, status, authority);
	}

	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(loginId, password, 0, null, status, authority);
	}

	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId, password, 0, LocalDateTime.now(), status, authority);
	}
	
}
