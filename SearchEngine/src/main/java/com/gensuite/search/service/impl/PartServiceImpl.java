package com.gensuite.search.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gensuite.search.dao.PartDAO;
import com.gensuite.search.entities.Description;
import com.gensuite.search.entities.Part;
import com.gensuite.search.repository.PartRepository;
import com.gensuite.search.service.PartService;


@Service("partService")
public class PartServiceImpl implements PartService{
	
	@Resource
	private PartRepository partRepository;
	
	@Autowired
	private PartDAO partDao;
	
	
	@Override
	public void populatePartIndex() {
		partRepository.deleteAll();
		System.out.println("deleted");
		List<Part> partList = partDao.getPartList();
		System.out.println("partList-----------"+partList.size());
		int i=0;
		List<Part> partSubList = partList.subList(67364, partList.size());
		partRepository.save(partList);
		/*for(Part part : partList){
			i++;
			if(i>67364){
				partRepository.index(part);
			}
		}*/
	}


	@Override
	public Long getNumberOfParts() {
		// TODO Auto-generated method stub
		return partRepository.count();
	}

	
	/*@Override
	public Map<Long, List<Description>> getDescriptionMap() {
		return partDao.getDescriptionMap();
	}*/
}
