/**
 * 
 */
package com.cer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Praveen Kumar
 *
 */
public class JuniorNiftyStockWatch {
	private String trdVolumesumMil;
	private String time;
	private List<NiftDataModel1> latestData = new ArrayList<NiftDataModel1>();
	private Integer declines;
	private String trdValueSum;
	private String trdValueSumMil;
	private String unchanged;
	private String trdVolumesum;
	private String advances;
	private List<NiftyModel2> data = new ArrayList<NiftyModel2>();
	public String getTrdVolumesumMil() {
		return trdVolumesumMil;
	}
	public String getTime() {
		return time;
	}
	public List<NiftDataModel1> getLatestData() {
		return latestData;
	}
	public Integer getDeclines() {
		return declines;
	}
	public String getTrdValueSum() {
		return trdValueSum;
	}
	public String getTrdValueSumMil() {
		return trdValueSumMil;
	}
	public String getUnchanged() {
		return unchanged;
	}
	public String getTrdVolumesum() {
		return trdVolumesum;
	}
	public String getAdvances() {
		return advances;
	}
	public List<NiftyModel2> getData() {
		return data;
	}
	public void setTrdVolumesumMil(String trdVolumesumMil) {
		this.trdVolumesumMil = trdVolumesumMil;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setLatestData(List<NiftDataModel1> latestData) {
		this.latestData = latestData;
	}
	
	public void setLatestData(NiftDataModel1 latestData1) {
		this.latestData.add(latestData1);
	}
	public void setDeclines(Integer declines) {
		this.declines = declines;
	}
	public void setTrdValueSum(String trdValueSum) {
		this.trdValueSum = trdValueSum;
	}
	public void setTrdValueSumMil(String trdValueSumMil) {
		this.trdValueSumMil = trdValueSumMil;
	}
	public void setUnchanged(String unchanged) {
		this.unchanged = unchanged;
	}
	public void setTrdVolumesum(String trdVolumesum) {
		this.trdVolumesum = trdVolumesum;
	}
	public void setAdvances(String advances) {
		this.advances = advances;
	}
	public void setData(List<NiftyModel2> data) {
		this.data = data;
	}
	
	public void setData(NiftyModel2 data1) {
		this.data.add( data1);
	}
}
