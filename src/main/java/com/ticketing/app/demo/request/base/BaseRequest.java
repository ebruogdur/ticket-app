package com.ticketing.app.demo.request.base;

import java.io.Serializable;
import java.util.List;

public interface BaseRequest<T> extends Serializable {

	public T get();
	
	default public List<T> getList() {
		return null;
	}

}

