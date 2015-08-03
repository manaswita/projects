package com.gensuite.search.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gensuite.search.entities.Catalog;

public interface CatalogRepository extends ElasticsearchRepository<Catalog,String>{
	List<Catalog> findByCatalogName(String catalogName,Pageable pageable);
	List<Catalog> findByCatalogNameStartsWith(String catalogName);
	Catalog findByCatalogId(Long catalogId);
	List<Catalog> findByCatalogNameLike(String catalogName);
}
