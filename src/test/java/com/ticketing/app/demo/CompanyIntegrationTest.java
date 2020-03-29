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
import com.ticketing.app.demo.dao.CompanyDao;
import com.ticketing.app.demo.dto.CompanyDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Company;
import com.ticketing.app.demo.request.CompanyRequest;
import com.ticketing.app.demo.response.CompanyResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
	@Value("${app.company}")
	protected Long companyId;
	@Autowired
	private CompanyDao dao;
	private UUID uuid = UUID.randomUUID();
	@Autowired
	protected MockMvc mvc;
	private String url = "http://localhost:8080/company";

	@BeforeEach
	public void initDb() {
		dao.deleteAll();
		Company model = new Company();
		model.setId(1L);
		model.setUuid(uuid);
		model.setCreatedBy(1L);
		model.setCreatedTime(new Date());
		model.setLastUpdatedBy(1L);
		model.setLastUpdatedTime(new Date());
		model.setStatus(1);
		model.setName("A");
		model.setDescription("A");
		model.setCodeName("A");
		model.setDomainName("A");
		model.setFormalName("A");
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
		CompanyResponse res = mapper.readValue(json, CompanyResponse.class);
		CompanyDto dto = res.getAppCompanies();
		assertEquals(dto.getUuid(), uuid);
	}

	@Test
	public void saveTest() throws IOException, Exception {
		CompanyRequest req = new CompanyRequest();
		CompanyDto dto = new CompanyDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(1);
		dto.setName("B");
		dto.setDescription("B");
		dto.setCodeName("B");
		dto.setDomainName("B");
		dto.setFormalName("B");
		req.setAppCompanies(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		List<Company> list = dao.findAll();
		assertThat(list).hasSize(2);
	}

	@Test
	public void updateTest() throws IOException, Exception {
		CompanyRequest req = new CompanyRequest();
		CompanyDto dto = new CompanyDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(1);
		dto.setName("C");
		dto.setDescription("C");
		dto.setCodeName("C");
		dto.setDomainName("C");
		dto.setFormalName("C");
		req.setAppCompanies(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(put(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		List<Company> list = dao.findAll();
		assertThat(list).hasSize(1);
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		List<Company> list = dao.findAll();
		assertThat(list).hasSize(1);
		assertEquals(list.get(0).getStatus(), Status.DELETED.getCode());
	}
}
