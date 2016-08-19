package com.cer.model;

import java.util.ArrayList;
import java.util.List;

public class NSELiveStockWatch {
	private String symbol;
	private List<String> otherSeries ;
	private String lastUpdateTime ;
	private String tradedDate ;
	private List<NSEDataModel2> data = new ArrayList<NSEDataModel2>() ;
	private String optLink ;
	public String getSymbol() {
		return symbol;
	}
	public List<String> getOtherSeries() {
		return otherSeries;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public String getTradedDate() {
		return tradedDate;
	}
	public List<NSEDataModel2> getData() {
		return data;
	}
	public String getOptLink() {
		return optLink;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setOtherSeries(List<String> otherSeries) {
		this.otherSeries = otherSeries;
	}
	public void setOtherSeries(String otherSeries) {
		this.otherSeries.add(otherSeries) ;
	}
	
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public void setTradedDate(String tradedDate) {
		this.tradedDate = tradedDate;
	}
	public void setData(List<NSEDataModel2> data) {
		this.data = data;
	}
	
	public void setData(NSEDataModel2 data) {
		this.data.add(data);
	}
	
	public void setOptLink(String optLink) {
		this.optLink = optLink;
	}

}
