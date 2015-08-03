package com.gensuite.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "gensuite",type = "catalog", indexStoreType = "memory")
public class Catalog {
	
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
    
    public Catalog(){
    	
    }
    public Catalog(Long catalogId,String catalogName,String catalogEngineeringDescription,String catalogCommercialDescription,
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
}
