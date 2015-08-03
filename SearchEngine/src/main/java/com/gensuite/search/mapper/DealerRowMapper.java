package com.gensuite.search.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gensuite.search.entities.Dealer;

public class DealerRowMapper implements RowMapper<Dealer>{

	@Override
	public Dealer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Dealer dealer = new Dealer();
		dealer.setIntermediaryId(rs.getLong("INTERMEDIARY_ID"));
		dealer.setIntermediaryName(rs.getString("INTERMEDIARY_NAME"));
		dealer.setIntermediaryDescription(rs.getString("INTERMEDIARY_DESCRIPTION"));
		dealer.setIntermediaryCode(rs.getString("INTERMEDIARY_CODE"));
		dealer.setCity(rs.getString("city"));
		dealer.setState(rs.getString("state"));
		dealer.setZipCode(rs.getString("zipCode"));
		
		dealer.setIntermediaryType(rs.getString("INTERMEDIARY_TYPE"));
		dealer.setAddress1(rs.getString("ADDRESS1"));
		dealer.setAddress2(rs.getString("ADDRESS2"));
		dealer.setAddress3(rs.getString("ADDRESS3"));
		dealer.setCountry(rs.getString("COUNTRY"));
		dealer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
		dealer.setFax(rs.getString("FAX"));
		
		return dealer;
	}
}
