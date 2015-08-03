package com.gensuite.search.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gensuite.search.dao.DealerDao;
import com.gensuite.search.entities.Dealer;
import com.gensuite.search.repository.DealerRepository;
import com.gensuite.search.service.DealerService;

@Service("dealerService")
public class DealerServiceImpl implements DealerService{
	
	@Resource
	private DealerRepository dealerRepository;
	
	@Autowired
	private DealerDao dealerDao;
	
	@Override
	public void populateDealerIndex() {
		//dealerRepository.deleteAll();

		List<Dealer> dealerList = dealerDao.getDealerList();
		System.out.println("deleted===="+dealerList.size());
		dealerRepository.save(dealerList);
	}
}
