package com.gensuite.search.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "gensuite2", indexStoreType = "memory")
public class ValueLable {
	
	@Id
    private String Lable;
    private String Value;
    
    public String getLable() {
		return Lable;
	}
	public void setLable(String lable) {
		Lable = lable;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
  
}
