package com.gensuite.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gensuite.search.dao.CatalogDao;
import com.gensuite.search.entities.Catalog;
import com.gensuite.search.repository.CatalogRepository;
import com.gensuite.search.service.CatalogService;

@Service("catalogService")
public class CatalogServiceImpl implements CatalogService{
	
	@Resource
	private CatalogRepository catalogRepository;
	
	@Autowired
	private CatalogDao catalogDao;
	
	public CatalogServiceImpl() {
		super();
	}
	
	@Override
	public void populateCatalogIndex() {
		catalogRepository.deleteAll();

		List<Catalog> catalogList = catalogDao.getCatalogList();
		System.out.println("deleted===="+catalogList.size());
		catalogRepository.save(catalogList);
	}
}
