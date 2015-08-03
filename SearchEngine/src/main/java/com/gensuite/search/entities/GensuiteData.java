package com.gensuite.search.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Document(indexName = "great", indexStoreType = "memory")
public class GensuiteData {
	
	@Id
	private Long catalogId;
	private String catalogName;
	private String catalogEngineeringDescription;
	private String catalogCommercialDescription;
    private String customerName;
    private Long mediaId;
    private String webPath;
    private String thumbnailPath;
    private String exportfilePath;
    private Long organizationId;
    private String organizationName;
    private String model;
    private String catalogReference;
    private Long partId;
    private String partNumber;
    
    @Field( type = FieldType.Nested)
    private List<Description> description;
    
    private List<String> legacyPartNumber;
    private List<String> vendorPartNumber;
    
    /*private Long mediaId;*/
    private String webpath;
    private String thumbNailPath;
    private String exportFilePath;
    private String orderable;
    
    public GensuiteData(){
    	
    }
    public GensuiteData(Long catalogId,String catalogName,String catalogEngineeringDescription,String catalogCommercialDescription,
    		String customerName,Long mediaId,String webPath,String thumbnailPath,String exportfilePath,Long organizationId,String organizationName,
    		String model,String catalogReference, String highlightedMessage){
    		this.catalogId = catalogId;
    		this.catalogName = catalogName;
    		this.catalogEngineeringDescription = catalogEngineeringDescription;
    		this.catalogCommercialDescription = catalogCommercialDescription;
    		this.customerName = customerName;
    		this.mediaId = mediaId;
    		this.webPath = webPath;
    		this.thumbnailPath = thumbnailPath;
    		this.exportfilePath = exportfilePath;
    		this.organizationId = organizationId;
    		this.organizationName = organizationName;
    		this.model = model;
    		this.catalogReference = catalogReference;
    }
    
	public Long getCatalogId() {
		return catalogId;
	}
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}
	public String getCatalogName() {
		return catalogName;
	}
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	public String getCatalogEngineeringDescription() {
		return catalogEngineeringDescription;
	}
	public void setCatalogEngineeringDescription(
			String catalogEngineeringDescription) {
		this.catalogEngineeringDescription = catalogEngineeringDescription;
	}
	public String getCatalogCommercialDescription() {
		return catalogCommercialDescription;
	}
	public void setCatalogCommercialDescription(String catalogCommercialDescription) {
		this.catalogCommercialDescription = catalogCommercialDescription;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
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
	public String getThumbnailPath() {
		return thumbnailPath;
	}
	public void setThumbnailPath(String thumbnailPath) {
		this.thumbnailPath = thumbnailPath;
	}
	public String getExportfilePath() {
		return exportfilePath;
	}
	public void setExportfilePath(String exportfilePath) {
		this.exportfilePath = exportfilePath;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getOrganizationName() {
		return organizationName;
	}
	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCatalogReference() {
		return catalogReference;
	}
	public void setCatalogReference(String catalogReference) {
		this.catalogReference = catalogReference;
	}
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
	
	public String getWebpath() {
		return webpath;
	}
	public void setWebpath(String webpath) {
		this.webpath = webpath;
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
