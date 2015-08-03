package com.gensuite.search.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.gensuite.search.entities.Dealer;

public interface DealerRepository extends ElasticsearchRepository<Dealer,String>{
	
}
