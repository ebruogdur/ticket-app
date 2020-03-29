package com.ticketing.app.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ticketing.app.demo.dto.CompanyDto;
import com.ticketing.app.demo.enums.Message;
import com.ticketing.app.demo.enums.Status;
import com.ticketing.app.demo.model.Company;
import com.ticketing.app.demo.request.CompanyRequest;
import com.ticketing.app.demo.response.CompanyResponse;
import com.ticketing.app.demo.service.CompanyService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	String message = Message.FAIL.getCode();

	@ApiOperation(value = "Get company by uuid", notes = "Return company by uuid ")
	@GetMapping(value = "/{uuid}")
	public CompanyResponse getAppCompanies(@PathVariable("uuid") UUID uuid) {
		CompanyDto companytDto = companyService.get(uuid);
		if (companytDto != null) {
			message = Message.SUCCESS.getCode();
		}
		return new CompanyResponse(companytDto, message);
	}

	@ApiOperation(value = "Insert company", notes = "Insert company ")
	@PostMapping()
	public String saveAppCompanies(@RequestBody CompanyRequest req) {
		companyService.save(req.getAppCompanies());
		if (req.getAppCompanies() != null) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Update company by uuid", notes = "Update company by uuid")
	@PutMapping(value = "/{uuid}")
	public void updateAppCompanies(@RequestBody CompanyRequest req, @PathVariable("uuid") UUID uuid) {
		companyService.update(uuid, req.getAppCompanies());
	}

	@ApiOperation(value = "Delete company by uuid", notes = "Delete company by uuid")
	@DeleteMapping(value = "/{uuid}")
	public String deleteAppCompanies(@PathVariable("uuid") UUID uuid) {
		CompanyDto companyDto = companyService.get(uuid);
		companyService.delete(uuid);
		if (companyDto.getStatus() == Status.DELETED.getCode()) {
			message = Message.SUCCESS.getCode();
		}
		return message;
	}

	@ApiOperation(value = "Search company by name", notes = "Search company by name")
	@PostMapping(value = "/searchbyname")
	public List<Company> searchAppCompanies(@RequestBody String name) {
		return companyService.searchByName(name);
	}
}
