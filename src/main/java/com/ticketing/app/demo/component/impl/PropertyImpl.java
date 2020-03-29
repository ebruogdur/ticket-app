package com.ticketing.app.demo.component.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ticketing.app.demo.component.Property;

@Component
public class PropertyImpl implements Property {
	@Value("${app.company}")
	Long companyId;
	@Value("${app.version}")
	String version;

	@Override
	public Long getCompanyId() {
		return companyId;
	}

	@Override
	public String getVersion() {
		return version;
	}
}