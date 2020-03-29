package com.ticketing.app.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.math.BigDecimal;
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
import com.ticketing.app.demo.dao.FlightDao;
import com.ticketing.app.demo.dto.FlightDto;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Flight;
import com.ticketing.app.demo.request.FlightRequest;
import com.ticketing.app.demo.response.FlightResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class FlightIntegrationTest {
	@Value("${app.company}")
	protected Long companyId;
	private UUID uuid = UUID.randomUUID();
	@Autowired
	FlightDao dao;
	@Autowired
	protected MockMvc mvc;
	private String url = "http://localhost:8080/flight";

	@BeforeEach
	public void initDb() {
		dao.deleteAll();
		Flight model = new Flight();
		model.setUuid(uuid);
		model.setId(1L);
		model.setCreatedBy(1L);
		model.setCreatedTime(new Date());
		model.setLastUpdatedBy(1L);
		model.setLastUpdatedTime(new Date());
		model.setStatus(1);
		model.setCode("a");
		model.setCompanyId(1L);
		model.setFlightDate(new Date());
		model.setFlightDurationInMinutes(60);
		model.setFromAirportId(1L);
		model.setPrice(new BigDecimal(100));
		model.setQuota((long) 10);
		model.setRouteId(1L);
		model.setToAirportId(1L);
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
		FlightResponse res = mapper.readValue(json, FlightResponse.class);
		FlightDto dto = res.getAppFlights();
		assertEquals(dto.getUuid(), uuid);
	}

	@Test
	public void saveTest() throws IOException, Exception {
		FlightRequest req = new FlightRequest();
		FlightDto dto = new FlightDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(1);
		dto.setCode("a");
		dto.setCompanyId(1L);
		dto.setFlightDate(new Date());
		dto.setFlightDurationInMinutes(60);
		dto.setFromAirportId(1L);
		dto.setPrice(new BigDecimal(300));
		dto.setQuota((long) 10);
		dto.setRouteId(1L);
		dto.setToAirportId(1L);
		req.setAppFlights(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		List<Flight> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(2);
	}

	@Test
	public void updateTest() throws IOException, Exception {
		FlightRequest req = new FlightRequest();
		FlightDto dto = new FlightDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(2);
		dto.setCode("b");
		dto.setCompanyId(1L);
		dto.setFlightDate(new Date());
		dto.setFlightDurationInMinutes(60);
		dto.setFromAirportId(1L);
		dto.setPrice(new BigDecimal(3000));
		dto.setQuota((long) 100);
		dto.setRouteId(1L);
		dto.setToAirportId(1L);
		req.setAppFlights(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(put(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		List<Flight> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		List<Flight> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
		assertEquals(list.get(0).getStatus(), Status.DELETED.getCode());
	}
}
