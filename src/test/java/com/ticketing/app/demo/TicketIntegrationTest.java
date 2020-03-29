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
import com.ticketing.app.demo.dao.TicketDao;
import com.ticketing.app.demo.dto.TicketDto;
import com.ticketing.app.demo.enums.Cabin;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Flight;
import com.ticketing.app.demo.model.Ticket;
import com.ticketing.app.demo.request.TicketRequest;
import com.ticketing.app.demo.response.TicketResponse;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = DemoApplication.class)
@AutoConfigureMockMvc
public class TicketIntegrationTest {
	@Value("${app.company}")
	protected Long companyId;
	private UUID uuid = UUID.randomUUID();
	@Autowired
	TicketDao dao;
	@Autowired
	FlightDao flightDao;
	@Autowired
	protected MockMvc mvc;
	private String url = "http://localhost:8080/ticket";

	@BeforeEach
	public void initDb() {
		dao.deleteAll();
		flightDao.deleteAll();
		Flight flightModel = new Flight();
		flightModel.setUuid(uuid);
		flightModel.setId(1L);
		flightModel.setCreatedBy(1L);
		flightModel.setCreatedTime(new Date());
		flightModel.setLastUpdatedBy(1L);
		flightModel.setLastUpdatedTime(new Date());
		flightModel.setStatus(1);
		flightModel.setCode("a");
		flightModel.setCompanyId(1L);
		flightModel.setFlightDate(new Date());
		flightModel.setFlightDurationInMinutes(60);
		flightModel.setFromAirportId(1L);
		flightModel.setPrice(new BigDecimal(100));
		flightModel.setQuota((long) 10);
		flightModel.setRouteId(1L);
		flightModel.setToAirportId(1L);
		flightDao.save(flightModel);
		Flight findByCompanyIdAndUuid = flightDao.findByCompanyIdAndUuid(companyId, uuid);
		Ticket model = new Ticket();
		model.setUuid(uuid);
		model.setId(5L);
		model.setCreatedBy(1L);
		model.setCreatedTime(new Date());
		model.setLastUpdatedBy(1L);
		model.setLastUpdatedTime(new Date());
		model.setStatus(1);
		model.setCompanyId(1L);
		model.setAmount(new BigDecimal(100));
		model.setCabin(Cabin.ECONOMY.getCode());
		model.setCurrency("TRY");
		model.setFlightId(findByCompanyIdAndUuid.getId());
		model.setGateNo("108");
		model.setPassengerIdentityCardNo("42565896412");
		model.setPassengerName("a");
		model.setPassengerSurname("a");
		model.setSeatNo("A10");
		model.setTicketNumber("A12345");
		model.setVat(new BigDecimal(18));
		dao.save(model);
	}

	@AfterEach
	public void resetDb() {
		dao.deleteAll();
		flightDao.deleteAll();
	}

	@Test
	public void getTest() throws IOException, Exception {
		byte[] json = mvc.perform(get(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		TicketResponse res = mapper.readValue(json, TicketResponse.class);
		TicketDto dto = res.getAppTickets();
		assertEquals(dto.getUuid(), uuid);
	}

	@Test
	public void saveTest() throws IOException, Exception {
		Flight findByCompanyIdAndUuid = flightDao.findByCompanyIdAndUuid(companyId, uuid);
		TicketRequest req = new TicketRequest();
		TicketDto dto = new TicketDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(1);
		dto.setCompanyId(1L);
		dto.setAmount(new BigDecimal(100));
		dto.setCabin(Cabin.ECONOMY.getCode());
		dto.setCurrency("TRY");
		dto.setFlightId(findByCompanyIdAndUuid.getId());
		dto.setGateNo("108");
		dto.setPassengerIdentityCardNo("42565896412");
		dto.setPassengerName("a");
		dto.setPassengerSurname("a");
		dto.setSeatNo("A10");
		dto.setTicketNumber("A12345");
		dto.setVat(new BigDecimal(18));
		req.setAppTickets(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(post(url).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json)).andExpect(status().isOk());
		List<Ticket> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(2);
	}

	@Test
	public void updateTest() throws IOException, Exception {
		TicketRequest req = new TicketRequest();
		TicketDto dto = new TicketDto();
		dto.setCreatedBy(1L);
		dto.setCreatedTime(new Date());
		dto.setLastUpdatedBy(1L);
		dto.setLastUpdatedTime(new Date());
		dto.setStatus(2);
		dto.setCompanyId(1L);
		dto.setAmount(new BigDecimal(100));
		dto.setCabin(Cabin.ECONOMY.getCode());
		dto.setCurrency("USD");
		dto.setFlightId(1L);
		dto.setGateNo("308");
		dto.setPassengerIdentityCardNo("42565896412");
		dto.setPassengerName("a");
		dto.setPassengerSurname("a");
		dto.setSeatNo("C10");
		dto.setTicketNumber("A12345");
		dto.setVat(new BigDecimal(18));
		req.setAppTickets(dto);
		ObjectMapper mapper = new ObjectMapper();
		byte[] json = mapper.writeValueAsBytes(req);
		mvc.perform(put(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
		List<Ticket> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
	}

	@Test
	public void deleteTest() throws Exception {
		mvc.perform(delete(url + "/" + uuid.toString()).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		List<Ticket> list = dao.findByCompanyId(companyId);
		assertThat(list).hasSize(1);
		assertEquals(list.get(0).getStatus(), Status.DELETED.getCode());
	}

	@Test
	public void cancelTest() throws Exception {
		String ticketNumber = "A12345";
		mvc.perform(post(url + "/cancel").contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(ticketNumber))
				.andExpect(status().isOk());
		Ticket findByCompanyIdAndUuid = dao.findByCompanyIdAndTicketNumber(companyId, ticketNumber);
		assertEquals(findByCompanyIdAndUuid.getStatus(), Status.PASSIVE.getCode());
	}

	@Test
	public void calculateAmountTest() throws Exception {
		Flight findByCompanyIdAndUuid = flightDao.findByCompanyIdAndUuid(companyId, uuid);
		BigDecimal price = findByCompanyIdAndUuid.getPrice();
		BigDecimal total = price.add(price.multiply(new BigDecimal(10)).divide(new BigDecimal(100)));
		assertEquals(110, total.intValue());
	}
}
