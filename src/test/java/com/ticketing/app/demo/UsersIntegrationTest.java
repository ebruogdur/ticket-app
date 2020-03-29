package com.ticketing.app.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketing.app.demo.dao.UserDao;
import com.ticketing.app.demo.dto.UserDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.User;
import com.ticketing.app.demo.request.UserRequest;
import com.ticketing.app.demo.response.UserResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class UsersIntegrationTest {
	@Value("${app.company}")
	protected Long companyId;
	private UUID uuid = UUID.randomUUID();
	@Autowired
	UserDao dao;
	@Autowired
	protected MockMvc mvc;
	private String url = "http://localhost:8080/user";

	@BeforeEach
	public void initDb() {
		dao.deleteAll();
		User model = new User();
		model.setUuid(uuid);
		model.setId(5L);
		model.setCreatedBy(1L);
		model.setCreatedTime(new Date());
		model.setLastUpdatedBy(1L);
		model.setLastUpdatedTime(new Date());
		model.setStatus(1);
		model.setCompanyId(1L);
		model.setFullName("xyz xyz");
		model.setLanguageCode("tr");
		model.setLastLoginTime(new Date());
		model.setLastPasswordUpdateTime(new Date());
		model.setPasswordHashSha512("123");
		model.setTimezone("Europe/istanbul");
		model.setUserEmail("xyz@gmail.com");
		model.setUserName("x");
		dao.save(model);
	}

	@AfterEach
	public void resetDb() {
		dao.deleteAll();
	}

	@Test
	public void getTest() throws IOException, Exception {
		
		byte[] json = mvc.perform(get(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		UserResponse res = mapper.readValue(json, UserResponse.class);
		UserDto dto = res.getAppUsers();

		assertEquals(dto.getUuid(), uuid);
	}

	@Test
	public void saveTest() throws IOException, Exception {
		UserRequest req = new UserRequest();
		UserDto dto = new UserDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(1);
		dto.setCompanyId(1L);
		dto.setFullName("abc abc");
		dto.setLanguageCode("tr");
		dto.setLastLoginTime(new Date());
		dto.setLastPasswordUpdateTime(new Date());
		dto.setPasswordHashSha512("123");
		dto.setTimezone("Europe/istanbul");
		dto.setUserEmail("abc@gmail.com");
		dto.setUserName("a");
		req.setAppUsers(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		List<User> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(2);
	}

	@Test
	public void updateTest() throws IOException, Exception {
		UserRequest req = new UserRequest();
		UserDto dto = new UserDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(2);
		dto.setCompanyId(1L);
		dto.setFullName("klm klm");
		dto.setLanguageCode("tr");
		dto.setLastLoginTime(new Date());
		dto.setLastPasswordUpdateTime(new Date());
		dto.setPasswordHashSha512("123");
		dto.setTimezone("Europe/istanbul");
		dto.setUserEmail("klm@gmail.com");
		dto.setUserName("k");
		req.setAppUsers(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(put(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		List<User> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		List<User> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
		assertEquals(list.get(0).getStatus(), Status.DELETED.getCode());
	}
}
