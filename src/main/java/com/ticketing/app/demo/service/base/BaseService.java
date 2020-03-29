package com.ticketing.app.demo.service.base;

import java.util.UUID;

public interface BaseService<T> {
	default public Object getValue(Long id, String key) {
		return null;
	}

	public T get(UUID uuid);

	public void save(T dto);

	public void update(UUID uuid, T dto);

	public void delete(UUID uuid);
}
