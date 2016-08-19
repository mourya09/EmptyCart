package com.cer.model;

import java.sql.Timestamp;

public class NSEBaseModel {
	
	private Timestamp recordTimeStamp;
	private String country="India";
	private TypeOfExchange type = TypeOfExchange.NSE;
	public Timestamp getRecordTimeStamp() {
		return recordTimeStamp;
	}
	public String getCountry() {
		return country;
	}
	public TypeOfExchange getType() {
		return type;
	}
	public void setRecordTimeStamp(Timestamp recordTimeStamp) {
		this.recordTimeStamp = recordTimeStamp;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setType(TypeOfExchange type) {
		this.type = type;
	}

}
