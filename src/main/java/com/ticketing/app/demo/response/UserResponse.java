package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.UserDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public UserResponse(UserDto appUsers) {
		this.appUsers = appUsers;
	}

	@JsonProperty("user")
	@JsonInclude(Include.NON_NULL)
	private UserDto appUsers;

	public UserResponse(List<UserDto> appUsersList) {
		this.appUsersList = appUsersList;
	}

	@JsonProperty("users")
	@JsonInclude(Include.NON_NULL)
	private List<UserDto> appUsersList;
}
