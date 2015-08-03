package com.gensuite.search.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gensuite.search.dao.DealerDao;
import com.gensuite.search.entities.Dealer;
import com.gensuite.search.mapper.DealerRowMapper;

@Repository
public class DealerDaoImpl implements DealerDao{
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public List<Dealer> getDealerList() {
		String selectDealerSql = "select I.INTERMEDIARY_ID, I.INTERMEDIARY_NAME, I.INTERMEDIARY_DESCRIPTION, " +
				"BILL_TO_ADDRESS_ID,SHIP_TO_ADDRESS_ID ,ACTIVE ,I.INTERMEDIARY_TYPE_ID,I.INTERMEDIARY_CODE,ADDRESS1,ADDRESS2,ADDRESS3,COUNTRY,PHONE_NUMBER,FAX," +
				"CITY,STATE,ZIPCODE, INTERMEDIARY_NAME " +
				"from INTERMEDIARY I, INTERMEDIARY_TYPE IT " +
				"where I.INTERMEDIARY_TYPE_ID=I.INTERMEDIARY_TYPE_ID and I.active= 1 and I.INTERMEDIARY_TYPE_ID=1";
		return jdbcTemplate. query(selectDealerSql,new DealerRowMapper());
	}
	

}
