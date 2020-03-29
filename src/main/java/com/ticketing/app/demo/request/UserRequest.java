package com.ticketing.app.demo.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.UserDto;
import com.ticketing.app.demo.request.base.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest implements BaseRequest<UserDto> {

	private static final long serialVersionUID = 8169827426318916655L;

	@JsonProperty("user")
	private UserDto appUsers;

	@Override
	public UserDto get() {
		return appUsers;
	}
}
