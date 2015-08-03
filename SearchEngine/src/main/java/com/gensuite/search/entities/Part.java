package com.gensuite.search.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "gensuite",type = "part", indexStoreType = "memory" )
public class Part {
	
	@Id
    private Long partId;
    private String partNumber;
    
    @Field( type = FieldType.Nested)
    private List<Description> description;
    
    private List<String> legacyPartNumber;
    private List<String> vendorPartNumber;
    
    private Long mediaId;
    private String webPath;
    private String thumbNailPath;
    private String exportFilePath;
    private String orderable;
    
	public Long getPartId() {
		return partId;
	}
	public void setPartId(Long partId) {
		this.partId = partId;
	}
	public String getPartNumber() {
		return partNumber;
	}
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	
	public Long getMediaId() {
		return mediaId;
	}
	public void setMediaId(Long mediaId) {
		this.mediaId = mediaId;
	}
	public String getWebPath() {
		return webPath;
	}
	public void setWebPath(String webPath) {
		this.webPath = webPath;
	}
	public String getThumbNailPath() {
		return thumbNailPath;
	}
	public void setThumbNailPath(String thumbNailPath) {
		this.thumbNailPath = thumbNailPath;
	}
	public String getExportFilePath() {
		return exportFilePath;
	}
	public void setExportFilePath(String exportFilePath) {
		this.exportFilePath = exportFilePath;
	}
	public List<Description> getDescription() {
		return description;
	}
	public void setDescription(List<Description> description) {
		this.description = description;
	}
	public List<String> getLegacyPartNumber() {
		return legacyPartNumber;
	}
	public void setLegacyPartNumber(List<String> legacyPartNumber) {
		this.legacyPartNumber = legacyPartNumber;
	}
	public List<String> getVendorPartNumber() {
		return vendorPartNumber;
	}
	public void setVendorPartNumber(List<String> vendorPartNumber) {
		this.vendorPartNumber = vendorPartNumber;
	}
	public String getOrderable() {
		return orderable;
	}
	public void setOrderable(String orderable) {
		this.orderable = orderable;
	}
}
