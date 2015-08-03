package com.gensuite.search.service;

import java.util.List;
import java.util.Map;

import com.gensuite.search.entities.Description;


public interface PartService {
	public void populatePartIndex();
	public Long getNumberOfParts();
	//public Map<Long, List<Description>> getDescriptionMap();
}
