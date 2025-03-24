package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
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
	private boolean isDisabled;

	@Column
	private String authority;

	public UserInfo() {
	}


	public UserInfo incrementLoginFailureCount() {
		return new UserInfo(loginId, password, ++loginFailureCount, accountLockedTime, isDisabled, authority);
	}

	public UserInfo resetLoginFailureInfo() {
		return new UserInfo(loginId, password, 0, null, isDisabled, authority);
	}

	public UserInfo updateAccountLocked() {
		return new UserInfo(loginId, password, 0, LocalDateTime.now(), isDisabled, authority);
	}
	
}
