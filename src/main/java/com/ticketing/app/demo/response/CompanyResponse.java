package com.ticketing.app.demo.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ticketing.app.demo.dto.CompanyDto;
import com.ticketing.app.demo.response.base.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyResponse implements BaseResponse {
	private static final long serialVersionUID = -4845185564920399602L;

	public CompanyResponse(CompanyDto appCompanies, String message) {
		this.appCompanies = appCompanies;
		this.message = message;
	}

	@JsonProperty("company")
	@JsonInclude(Include.NON_NULL)
	private CompanyDto appCompanies;

	public CompanyResponse(List<CompanyDto> appCompaniesList) {
		this.appCompaniesList = appCompaniesList;
	}

	@JsonProperty("companies")
	@JsonInclude(Include.NON_NULL)
	private List<CompanyDto> appCompaniesList;
	private String message;
}
