package com.gensuite.search.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gensuite.search.dao.CatalogDao;
import com.gensuite.search.entities.Catalog;
import com.gensuite.search.entities.Dealer;
import com.gensuite.search.mapper.CatalogRowMapper;
import com.gensuite.search.mapper.DealerRowMapper;

@Repository
public class CatalogDaoImpl implements CatalogDao{
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Catalog> getCatalogList() {
		String selectCatalogSql = "select c.catalog_id, c.catalog_name, c.catalog_engineering_description, c.catalog_commercial_description,	cm.customer_name ,c.media_id ,m.web_path,m.thumbnail_path,m.exportfile_path,c.organization_id,o.organization_name, c.model,c.catalog_reference  from catalog c left outer join media m on m.media_id=c.media_id, Customer cm ,organization o where cm.customer_id=c.customer_id and c.organization_id=o.organization_id";
		return jdbcTemplate. query(selectCatalogSql,new CatalogRowMapper());
	}
	

}
