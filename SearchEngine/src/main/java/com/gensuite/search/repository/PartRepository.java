package com.gensuite.search.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gensuite.search.entities.Part;

public interface PartRepository extends ElasticsearchRepository<Part,String>{
	List<Part> findByPartNumber(String partNumber);
	List<Part> findByPartNumberStartingWith(String partNumber);
	List<Part> findByPartNumberContaining(String partNumber);
}
