package com.gensuite.search.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.gensuite.search.entities.Part;

public class PartRowMapper implements RowMapper<Part> {

	@Override
	public Part mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
		Part part = new Part();
		
			
			part.setPartId(rs.getLong("part_id"));
			part.setPartNumber(rs.getString("part_number"));

			/*JAXBContext jcxml = JAXBContext.newInstance(xmlConversion.class);
			Unmarshaller unmarshallerDes = jcxml.createUnmarshaller();
			Marshaller marshallerDes = jcxml.createMarshaller();
			marshallerDes.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			StringReader srDes = new StringReader("<xmlConversion>"
					+ rs.getString("vendor_part_numbers")
					+ rs.getString("legacy_part_numbers")
					+ rs.getString("descriptions") + "</xmlConversion>");

			xmlConversion = (xmlConversion) unmarshallerDes.unmarshal(srDes);
			part.setLegacyPartNumbers(xmlConversion.getLegacyPartNumber());
			part.setDescription(xmlConversion.getDescription());
			part.setVendorPartNumbers(xmlConversion.getVendorPartNumber());
			System.out.println("here");*/
			
			part.setMediaId(rs.getLong("media_id"));
			part.setWebPath(rs.getString("web_path"));
			part.setThumbNailPath(rs.getString("thumbnail_path"));
			part.setExportFilePath(rs.getString("exportfile_path"));
			part.setOrderable(rs.getString("orderable"));

		

		return part;

	}
}
