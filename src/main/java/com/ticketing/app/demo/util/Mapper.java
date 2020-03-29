package com.ticketing.app.demo.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

public class Mapper<M, D> {
	private Class<M> modelClass;
	private Class<D> dtoClass;

	public Mapper(Class<M> modelClass, Class<D> dtoClass) {
		this.modelClass = modelClass;
		this.dtoClass = dtoClass;
	}

	public M dto2Model(D dto) {
		return dto2Model(dto, createModel());
	}

	public M dto2Model(D dto, M model) {
		if (dto != null) {
			BeanUtils.copyProperties(dto, model);
			return model;
		}
		return null;
	}

	public D model2Dto(M model) {
		return model2Dto(model, createDto());
	}

	public D model2Dto(M model, D dto) {
		if (model != null) {
			BeanUtils.copyProperties(model, dto);
			return dto;
		}
		return null;
	}

	public List<M> dto2Model(List<D> dtoList) {
		if (ListUtil.isNotEmpty(dtoList)) {
			List<M> modelList = new ArrayList<>();
			for (D dto : dtoList) {
				modelList.add(dto2Model(dto));
			}
			return modelList;
		}
		return null;
	}

	public List<D> model2Dto(List<M> modelList) {
		if (ListUtil.isNotEmpty(modelList)) {
			List<D> dtoList = new ArrayList<>();
			for (M model : modelList) {
				dtoList.add(model2Dto(model));
			}
			return dtoList;
		}
		return null;
	}

	public List<D> model2Dto(Page<M> modelList) {
		if (modelList.hasContent()) {
			Iterator<M> it = modelList.iterator();
			List<D> list = new ArrayList<>();
			while (it.hasNext()) {
				list.add(model2Dto(it.next()));
			}
			return list;
		}
		return null;
	}

	private M createModel() {
		try {
			return modelClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private D createDto() {
		try {
			return dtoClass.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
