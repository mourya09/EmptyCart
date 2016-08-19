/**
 * 
 */
package com.cer.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.cer.util.PropertyConfigurer;

/**
 * @author Praveen Kumar Stock and its symbol.
 *
 */
public class NiftyModel2  extends NSEBaseModel{
	private String symbol;
	private String open;
	private String high;

	private String low;
	private String ltP;
	//Change
	private String ptsC;
	//percentTageChange
	private String per;
	//Volumne
	private String trdVol;
	
	private String trdVolM;
	
	//Turnover in crores
	private String ntP;
	
	private String mVal;
	
	//52 Week High
	private String wkhi;
	
	//52 Week low
	private String wklo;
	
	
	private String wkhicm_adj;
	private String wklocm_adj;
	private String xDt;
	private String cAct;
	//365 % change 
	private String yPC;
	//30 days % change
	private String mPC;
	public String getSymbol() {
		return symbol;
	}
	public String getOpen() {
		return open;
	}
	public String getHigh() {
		return high;
	}
	public String getLow() {
		return low;
	}
	public String getLtP() {
		return ltP;
	}
	public String getPtsC() {
		return ptsC;
	}
	public String getPer() {
		return per;
	}
	public String getTrdVol() {
		return trdVol;
	}
	public String getTrdVolM() {
		return trdVolM;
	}
	public String getNtP() {
		return ntP;
	}
	public String getmVal() {
		return mVal;
	}
	public String getWkhi() {
		return wkhi;
	}
	public String getWklo() {
		return wklo;
	}
	public String getWkhicm_adj() {
		return wkhicm_adj;
	}
	public String getWklocm_adj() {
		return wklocm_adj;
	}
	public String getxDt() {
		return xDt;
	}
	public String getcAct() {
		return cAct;
	}
	public String getyPC() {
		return yPC;
	}
	public String getmPC() {
		return mPC;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setOpen(String open) {
		this.open = open;
	}
	public void setHigh(String high) {
		this.high = high;
	}
	public void setLow(String low) {
		this.low = low;
	}
	public void setLtP(String ltP) {
		this.ltP = ltP;
	}
	public void setPtsC(String ptsC) {
		this.ptsC = ptsC;
	}
	public void setPer(String per) {
		this.per = per;
	}
	public void setTrdVol(String trdVol) {
		this.trdVol = trdVol;
	}
	public void setTrdVolM(String trdVolM) {
		this.trdVolM = trdVolM;
	}
	public void setNtP(String ntP) {
		this.ntP = ntP;
	}
	public void setmVal(String mVal) {
		this.mVal = mVal;
	}
	public void setWkhi(String wkhi) {
		this.wkhi = wkhi;
	}
	public void setWklo(String wklo) {
		this.wklo = wklo;
	}
	public void setWkhicm_adj(String wkhicm_adj) {
		this.wkhicm_adj = wkhicm_adj;
	}
	public void setWklocm_adj(String wklocm_adj) {
		this.wklocm_adj = wklocm_adj;
	}
	public void setxDt(String xDt) {
		this.xDt = xDt;
	}
	public void setcAct(String cAct) {
		this.cAct = cAct;
	}
	public void setyPC(String yPC) {
		this.yPC = yPC;
	}
	public void setmPC(String mPC) {
		this.mPC = mPC;
	}
	
	

	

}
