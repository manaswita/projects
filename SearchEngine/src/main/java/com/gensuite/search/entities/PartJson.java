package com.gensuite.search.entities;

import java.util.List;

public class PartJson {

    int iTotalRecords;

    int iTotalDisplayRecords;

    String sEcho;

    String sColumns;

    List<Part> aaData;
    List<ValueLable> generalData;

    public int getiTotalRecords() {
	return iTotalRecords;
    }

    public void setiTotalRecords(int iTotalRecords) {
	this.iTotalRecords = iTotalRecords;
    }

    public int getiTotalDisplayRecords() {
	return iTotalDisplayRecords;
    }

    public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
	this.iTotalDisplayRecords = iTotalDisplayRecords;
    }

    public String getsEcho() {
	return sEcho;
    }

    public void setsEcho(String sEcho) {
	this.sEcho = sEcho;
    }

    public String getsColumns() {
	return sColumns;
    }

    public void setsColumns(String sColumns) {
	this.sColumns = sColumns;
    }

	public List<Part> getAaData() {
		return aaData;
	}

	public void setAaData(List<Part> aaData) {
		this.aaData = aaData;
	}
	
	public List<ValueLable> getGeneralData() {
		return generalData;
	}

	public void setGeneralData(List<ValueLable> generalData) {
		this.generalData = generalData;
	}
}
