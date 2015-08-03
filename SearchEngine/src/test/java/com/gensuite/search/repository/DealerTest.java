package com.gensuite.search.repository;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gensuite.search.service.DealerService;
import com.gensuite.search.service.PartService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring*.xml")
public class DealerTest {

	@Resource
	private DealerRepository dealerRepository;

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	DealerService dealerService;

	@Autowired
	PartService partService;


	@Test
	public void populateDealerIndex() {
		dealerService.populateDealerIndex();
	}

}
