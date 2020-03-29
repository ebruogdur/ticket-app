package com.ticketing.app.demo.service.impl;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketing.app.demo.component.Property;
import com.ticketing.app.demo.dao.UserDao;
import com.ticketing.app.demo.dto.UserDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.User;
import com.ticketing.app.demo.service.UserService;
import com.ticketing.app.demo.util.Mapper;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Autowired
	Property property;
	@Autowired
	UserDao appUsersDao;
	private final Mapper<User, UserDto> mapper = new Mapper<>(User.class, UserDto.class);

	@Override
	public UserDto get(UUID uuid) {
		return mapper.model2Dto(appUsersDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid));
	}

	@Override
	public void save(UserDto dto) {
		dto.setUuid(UUID.randomUUID());
		dto.setCompanyId(property.getCompanyId());
		appUsersDao.save(mapper.dto2Model(dto));
	}

	@Override
	public void update(UUID uuid, UserDto dto) {
		User appUsers = appUsersDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appUsers != null) {
			dto.setId(appUsers.getId());
			dto.setUuid(appUsers.getUuid());
			dto.setCompanyId(appUsers.getCompanyId());
			dto.setCreatedBy(appUsers.getCreatedBy());
			dto.setCreatedTime(appUsers.getCreatedTime());
			dto.setPasswordHashSha512(appUsers.getPasswordHashSha512());
			appUsersDao.save(mapper.dto2Model(dto, appUsers));
		}
	}

	@Override
	public void delete(UUID uuid) {
		User appUsers = appUsersDao.findByCompanyIdAndUuid(property.getCompanyId(), uuid);
		if (appUsers != null) {
			appUsers.setStatus(Status.DELETED.getCode());
			appUsersDao.save(appUsers);
		}
	}
}
