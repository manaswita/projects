package com.gensuite.search.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gensuite.search.entities.Catalog;

public class CatalogRowMapper implements RowMapper<Catalog>{

	@Override
	public Catalog mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Catalog catalog = new Catalog();
		catalog.setCatalogId(rs.getLong("catalog_id"));
		catalog.setCatalogName(rs.getString("catalog_name"));
		catalog.setCatalogEngineeringDescription(rs.getString("catalog_engineering_description"));
		catalog.setCatalogCommercialDescription(rs.getString("catalog_commercial_description"));
		catalog.setCustomerName(rs.getString("customer_name"));
		if(null != rs.getObject("media_id")){
			catalog.setMediaId(rs.getLong("media_id"));
		}else{
			catalog.setMediaId(null);
		}
		if(null != rs.getObject("web_path")){
			catalog.setWebPath(rs.getString("web_path"));
		}else{
			catalog.setWebPath("");
		}
		if(null != rs.getObject("thumbnail_path")){
			catalog.setThumbnailPath(rs.getString("thumbnail_path"));
		}else{
			catalog.setThumbnailPath("");
		}
		if(null != rs.getObject("exportfile_path")){
			catalog.setExportfilePath(rs.getString("exportfile_path"));
		}else{
			catalog.setExportfilePath("");
		}
		if(null != rs.getObject("organization_id")){
			catalog.setOrganizationId(rs.getLong("organization_id"));
		}else{
			catalog.setOrganizationId(null);
		}
		if(null != rs.getObject("organization_name")){
			catalog.setOrganizationName(rs.getString("organization_name"));
		}else{
			catalog.setOrganizationName("");
		}
		if(null != rs.getObject("model")){
			catalog.setModel(rs.getString("model"));
		}else{
			catalog.setModel("");
		}
		if(null != rs.getObject("catalog_reference")){
			catalog.setCatalogReference(rs.getString("catalog_reference"));
		}else{
			catalog.setCatalogReference("");
		}
		return catalog;
	}
}
