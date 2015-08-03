package com.gensuite.search.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gensuite.search.dao.PartDAO;
import com.gensuite.search.entities.Description;
import com.gensuite.search.entities.Part;
import com.gensuite.search.mapper.PartRowMapper;
import com.gensuite.search.repository.PartRepository;


@Repository
public class PartDaoImpl implements PartDAO {

	@Resource
	private PartRepository partRepository;
	
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public Map<Long, List<String>> getVendorMap() {
		String vendorsql = "select v.part_id, v.vendor_part_number from vendor_part v where v.active=1  ";
		final Map<Long, List<String>> vendorMap = new HashMap<Long, List<String>>();

		jdbcTemplate.query(vendorsql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Long part_id = rs.getLong("part_id");
					if (vendorMap.containsKey(part_id)) {
						List<String> vendorList = vendorMap.get(part_id);
						vendorList.add(rs.getString("vendor_part_number"));
						vendorMap.put(part_id, vendorList);
					} else {
						List<String> vendorList = new ArrayList<String>();
						vendorList.add(rs.getString("vendor_part_number"));
						vendorMap.put(part_id, vendorList);
					}
				}
				return vendorMap;
			};
		});
		return vendorMap;

	}

	public Map<Long, List<String>> getlegacyMap() {
		String legacysql = "SELECT l1.part_id,legacy_part_number FROM legacy_part l1 WHERE  l1.active=1 ";
		// jdbcTemplate.query(legacysql, new legacyNumRowMapper());
		final Map<Long, List<String>> legacyMap = new HashMap<Long, List<String>>();

		jdbcTemplate.query(legacysql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Long part_id = rs.getLong("part_id");
					if (legacyMap.containsKey(part_id)) {
						List<String> legacyList = legacyMap.get(part_id);
						legacyList.add(rs.getString("legacy_part_number"));
						legacyMap.put(part_id, legacyList);
					} else {
						List<String> legacyList = new ArrayList<String>();
						legacyList.add(rs.getString("legacy_part_number"));
						legacyMap.put(part_id, legacyList);
					}
				}
				return legacyMap;
			};
		});
		return legacyMap;

	}

	public Map<Long, List<Description>> getDescriptionMap() {
		String descriptionsql = "SELECT pd.part_id,pd.engineering_description  ,pd.commercial_description ,lg.language_name  FROM "
				+ " VW_PART_DESCRIPTION pd, language lg WHERE lg.language_id=pd.language_id  "
				+ " order by pd.part_id ";
		final Map<Long, List<Description>> descriptionMap = new HashMap<Long, List<Description>>();
		jdbcTemplate.query(descriptionsql, new ResultSetExtractor() {
			public Object extractData(ResultSet rs) throws SQLException {
				while (rs.next()) {
					Description desc = new Description();

					desc.setEngineeringDescription(rs
							.getString("engineering_description"));
					desc.setCommercialDescription(rs
							.getString("commercial_description"));
					desc.setLanguageName(rs.getString("language_name"));

					Long part_id = rs.getLong("part_id");
					if (descriptionMap.containsKey(part_id)) {
						List<Description> descriptionsList = descriptionMap
								.get(part_id);
						descriptionsList.add(desc);
						descriptionMap.put(part_id, descriptionsList);
					} else {
						List<Description> descriptionsList = new ArrayList<Description>();
						descriptionsList.add(desc);
						descriptionMap.put(part_id, descriptionsList);
					}
				}
				return descriptionMap;
			};
		});
		return descriptionMap;
	}

	public List<Part> getPartList() {
		Map<Long, List<Description>> descMap = getDescriptionMap();
		Map<Long, List<String>> vendorMap = getVendorMap();
		Map<Long, List<String>> legacyMap = getlegacyMap();

		String selectPartsql = "SELECT p1.part_id,part_number,m1.media_id , m1.web_path  , m1.thumbnail_path , m1.exportfile_path," +
				" CASE WHEN EXISTS (SELECT 1 FROM orderable_part op WHERE op.part_id=p1.part_id and op.active=1)  THEN 'true' else 'false' END AS orderable "
				+ " FROM part p1 left outer join part_media pm on pm.part_id=p1.part_id "
				+ " left outer join media m1 on m1.media_id= pm.media_id "
				+ " where p1.part_number not in (select catalog_name from catalog) "
				+ " and p1.is_topic is  null  ";

		List<Part> partList = jdbcTemplate.query(selectPartsql,
				new PartRowMapper());
		for (Part part : partList) {
			if (descMap.containsKey(part.getPartId())) {
				List<Description> descriptionList = descMap.get(part
						.getPartId());
				part.setDescription(descriptionList);
			}
			if (vendorMap.containsKey(part.getPartId())) {
				List<String> vendorList = vendorMap.get(part.getPartId());
				part.setVendorPartNumber(vendorList);
			}
			if (legacyMap.containsKey(part.getPartId())) {
				List<String> LegacyList = legacyMap.get(part.getPartId());
				part.setLegacyPartNumber(LegacyList);
			}
			
			//partRepository.save(part);
		}

		return partList;
	}
	/* 
	 * public List<Part> getPartList() { String selectPartsql =
	 * "SELECT p1.part_id,part_number, " +
	 * " (SELECT vendor_part_number as 'vendorPartNumber' FROM " +
	 * " vendor_part v1 WHERE v1.part_id=p1.part_id AND v1.active=1 " +
	 * " FOR XML PATH(''), type " + "    ) AS 'vendor_part_numbers', " +
	 * "   (SELECT legacy_part_number as 'legacyPartNumber' FROM " +
	 * "     legacy_part l1 WHERE l1.part_id=p1.part_id AND l1.active=1 " +
	 * "			FOR XML PATH(''), type " + "    )AS 'legacy_part_numbers', " +
	 * " (SELECT pd.engineering_description as 'engineeringDescription',pd.commercial_description as 'commercialDescription',lg.language_name as 'languageName' FROM "
	 * +
	 * " VW_PART_DESCRIPTION pd, language lg WHERE p1.part_id=pd.part_id  and lg.language_id=pd.language_id "
	 * + " FOR XML PATH('description'), type) as 'descriptions', " +
	 * " pm.media_id as 'mediaId', m.web_path as 'webPath' , m.thumbnail_path as 'thumbNailPath', m.exportfile_path as 'exportFilePath' "
	 * + "  FROM part p1, media m,  part_media pm " +
	 * "  WHERE p1.part_id BETWEEN 2 AND 100 and pm.part_id=p1.part_id and pm.media_id=m.media_id  "
	 * ; return jdbcTemplate.query(selectPartsql, new PartRowMapper()); }
	 */
}
