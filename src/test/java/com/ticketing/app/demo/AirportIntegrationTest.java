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
import com.ticketing.app.demo.dao.AirportDao;
import com.ticketing.app.demo.dto.AirportDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Airport;
import com.ticketing.app.demo.request.AirportRequest;
import com.ticketing.app.demo.response.AirportResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class AirportIntegrationTest {
	
	@Value("${app.company}")
	protected Long companyId;
	@Autowired
	private AirportDao dao;
	private UUID uuid = UUID.randomUUID();
	@Autowired
	protected MockMvc mvc;
	private String url = "http://localhost:8080/airport";

	@BeforeEach
	public void initDb() {
		dao.deleteAll();
		Airport model = new Airport();
		model.setUuid(uuid);
		model.setId(1L);
		model.setCreatedBy(1L);
		model.setCreatedTime(new Date());
		model.setLastUpdatedBy(1L);
		model.setLastUpdatedTime(new Date());
		model.setAddress("a");
		model.setCity("a");
		model.setCode("a");
		model.setCompanyId(companyId);
		model.setCountry("a");
		model.setGateCount(100);
		model.setName("a");
		model.setStatus(1);
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
		AirportResponse res = mapper.readValue(json, AirportResponse.class);
		AirportDto dto = res.getAppAirports();
		assertEquals(dto.getUuid(), uuid);
	}

	@Test
	public void saveTest() throws IOException, Exception {
		AirportRequest req = new AirportRequest();
		AirportDto dto = new AirportDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setAddress("a");
		dto.setCity("a");
		dto.setCode("a");
		dto.setCompanyId(companyId);
		dto.setCountry("a");
		dto.setGateCount(100);
		dto.setName("a");
		dto.setStatus(1);
		req.setAppAirports(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		List<Airport> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(2);
	}

	@Test
	public void updateTest() throws IOException, Exception {
		AirportRequest req = new AirportRequest();
		AirportDto dto = new AirportDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setAddress("a");
		dto.setCity("b");
		dto.setCode("b");
		dto.setCompanyId(companyId);
		dto.setCountry("a");
		dto.setGateCount(100);
		dto.setName("a");
		dto.setStatus(1);
		req.setAppAirports(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(put(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		List<Airport> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		List<Airport> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
		assertEquals(list.get(0).getStatus(), Status.DELETED.getCode());
	}
}
