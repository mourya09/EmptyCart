package com.cer.persistent;

public class LiveData {
	private String futLink;
	private String[] otherSeries;
	private String lastUpdateTime;
	private String tradedDate;
	private String optLink;
	private LiveInnerData[] data;
	

	public String getFutLink() {
		return futLink;
	}

	public String[] getOtherSeries() {
		return otherSeries;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public String getTradedDate() {
		return tradedDate;
	}

	public String getOptLink() {
		return optLink;
	}

	public LiveInnerData[] getData() {
		return data;
	}

	

	public void setFutLink(String futLink) {
		this.futLink = futLink;
	}

	public void setOtherSeries(String[] otherSeries) {
		this.otherSeries = otherSeries;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setTradedDate(String tradedDate) {
		this.tradedDate = tradedDate;
	}

	public void setOptLink(String optLink) {
		this.optLink = optLink;
	}

	public void setData(LiveInnerData[] data) {
		this.data = data;
	}

	
}
