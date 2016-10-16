package com.cer.persistent;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name= "live_shares")
public class LiveShares {
	@Id
	@GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
	@Column(name="id")
	private Long liveShareId;
	
	
	@OneToOne(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinColumn(name="symbol_id")
	private CompanySymbol symbolid;
	
	@Column(name="\"extremeLossMargin\"")
	private Double extremeLossMargin;
	
	@Column(name="\"lastUpdateTime\"")
	private Timestamp lastUpdateTime;
	
	 @Column(name="\"bcStartDate\"")
	 private   Timestamp bcStartDate;

	 @Column(name="\"ndStartDate\"")
	 private   Timestamp ndStartDate;

	 @Column(name="\"secDate\"")
	 private   Timestamp secDate;

	 @Column(name="\"cm_adj_low_dt\"")
	 private   Timestamp cm_adj_low_dt;

	 @Column(name="\"recordDate\"")
	 private   Timestamp recordDate;

	 @Column(name="\"cm_adj_high_dt\"")
	 private   Timestamp cm_adj_high_dt;

	 @Column(name="\"exDate\"")
	 private   Timestamp exDate;

	 @Column(name="\"bcEndDate\"")
	 private   Timestamp bcEndDate;

	 @Column(name="\"css_status_desc\"")
	 private   String css_status_desc;

	 @Column(name="\"ndEndDate\"")
	 private   Timestamp ndEndDate;


	 @Column(name="\"marketType\"")
	 private  String marketType;

	 @Column(name="\"companyName\"")
	 private  String companyName;

	 @Column(name="\"symbol\"")
	 private  String symbol;

	 @Column(name="\"isinCode\"")
	 private  String isinCode;

	 @Column(name="\"purpose\"")
	 private  String purpose;

	 @Column(name="\"series\"")
	 private  String series;


	 @Column(name="\"cm_ffm\"")
	 private  Double cm_ffm;

	 @Column(name="\"change\"")
	 private  Double change;

	 @Column(name="\"buyQuantity3\"")
	 private  Double buyQuantity3;

	 @Column(name="\"sellPrice1\"")
	 private  Double sellPrice1;

	 @Column(name="\"buyQuantity4\"")
	 private  Double buyQuantity4;

	 @Column(name="\"sellPrice2\"")
	 private  Double sellPrice2;

	 @Column(name="\"priceBand\"")
	 private  Double priceBand;

	 @Column(name="\"buyQuantity1\"")
	 private  Double buyQuantity1;

	 @Column(name="\"deliveryQuantity\"")
	 private  Double deliveryQuantity;

	 @Column(name="\"buyQuantity2\"")
	 private  Double buyQuantity2;

	 @Column(name="\"sellPrice5\"")
	 private  Double sellPrice5;

	 @Column(name="\"quantityTraded\"")
	 private  Double quantityTraded;

	 @Column(name="\"buyQuantity5\"")
	 private  Double buyQuantity5;

	 @Column(name="\"sellPrice3\"")
	 private  Double sellPrice3;

	 @Column(name="\"sellPrice4\"")
	 private  Double sellPrice4;

	 @Column(name="\"open\"")
	 private  Double open;

	 @Column(name="\"low52\"")
	 private  Double low52;

	 @Column(name="\"securityVar\"")
	 private  Double securityVar;

	 @Column(name="\"pricebandupper\"")
	 private  Double pricebandupper;

	 @Column(name="\"totalTradedValue\"")
	 private  Double totalTradedValue;

	 @Column(name="\"faceValue\"")
	 private  Double faceValue;

	 @Column(name="\"previousClose\"")
	 private  Double previousClose;

	 @Column(name="\"varMargin\"")
	 private  Double varMargin;

	 @Column(name="\"lastPrice\"")
	 private  Double lastPrice;

	 @Column(name="\"pChange\"")
	 private  Double pChange;

	 @Column(name="\"adhocMargin\"")
	 private  Double adhocMargin;

	 @Column(name="\"averagePrice\"")
	 private  Double averagePrice;

	 @Column(name="\"indexVar\"")
	 private  Double indexVar;

	 @Column(name="\"pricebandlower\"")
	 private  Double pricebandlower;

	 @Column(name="\"totalBuyQuantity\"")
	 private  Double totalBuyQuantity;

	 @Column(name="\"high52\"")
	 private  Double high52;

	 @Column(name="\"closePrice\"")
	 private  Double closePrice;

	 @Column(name="\"isExDateFlag\"")
	 private  Boolean isExDateFlag;

	 @Column(name="\"totalSellQuantity\"")
	 private  Double totalSellQuantity;

	 @Column(name="\"dayHigh\"")
	 private  Double dayHigh;

	 @Column(name="\"sellQuantity5\"")
	 private  Double sellQuantity5;

	 @Column(name="\"sellQuantity2\"")
	 private  Double sellQuantity2;

	 @Column(name="\"sellQuantity1\"")
	 private  Double sellQuantity1;

	 @Column(name="\"buyPrice1\"")
	 private  Double buyPrice1;

	 @Column(name="\"sellQuantity4\"")
	 private  Double sellQuantity4;

	 @Column(name="\"buyPrice2\"")
	 private  Double buyPrice2;

	 @Column(name="\"sellQuantity3\"")
	 private  Double sellQuantity3;

	 @Column(name="\"applicableMargin\"")
	 private  Double applicableMargin;

	 @Column(name="\"buyPrice4\"")
	 private  Double buyPrice4;

	 @Column(name="\"buyPrice3\"")
	 private  Double buyPrice3;

	 @Column(name="\"buyPrice5\"")
	 private  Double buyPrice5;

	 @Column(name="\"dayLow\"")
	 private  Double dayLow;

	 @Column(name="\"deliveryToTradedQuantity\"")
	 private  Double deliveryToTradedQuantity;

	 @Column(name="\"totalTradedVolume\"")
	 private  Double totalTradedVolume;

	public Long getLiveShareId() {
		return liveShareId;
	}

	public CompanySymbol getSymbolid() {
		return symbolid;
	}

	public Double getExtremeLossMargin() {
		return extremeLossMargin;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public Timestamp getBcStartDate() {
		return bcStartDate;
	}

	public Timestamp getNdStartDate() {
		return ndStartDate;
	}

	public Timestamp getSecDate() {
		return secDate;
	}

	public Timestamp getCm_adj_low_dt() {
		return cm_adj_low_dt;
	}

	public Timestamp getRecordDate() {
		return recordDate;
	}

	public Timestamp getCm_adj_high_dt() {
		return cm_adj_high_dt;
	}

	public Timestamp getExDate() {
		return exDate;
	}

	public Timestamp getBcEndDate() {
		return bcEndDate;
	}

	public String getCss_status_desc() {
		return css_status_desc;
	}

	public Timestamp getNdEndDate() {
		return ndEndDate;
	}

	public String getMarketType() {
		return marketType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getIsinCode() {
		return isinCode;
	}

	public String getPurpose() {
		return purpose;
	}

	public String getSeries() {
		return series;
	}

	public Double getCm_ffm() {
		return cm_ffm;
	}

	public Double getChange() {
		return change;
	}

	public Double getBuyQuantity3() {
		return buyQuantity3;
	}

	public Double getSellPrice1() {
		return sellPrice1;
	}

	public Double getBuyQuantity4() {
		return buyQuantity4;
	}

	public Double getSellPrice2() {
		return sellPrice2;
	}

	public Double getPriceBand() {
		return priceBand;
	}

	public Double getBuyQuantity1() {
		return buyQuantity1;
	}

	public Double getDeliveryQuantity() {
		return deliveryQuantity;
	}

	public Double getBuyQuantity2() {
		return buyQuantity2;
	}

	public Double getSellPrice5() {
		return sellPrice5;
	}

	public Double getQuantityTraded() {
		return quantityTraded;
	}

	public Double getBuyQuantity5() {
		return buyQuantity5;
	}

	public Double getSellPrice3() {
		return sellPrice3;
	}

	public Double getSellPrice4() {
		return sellPrice4;
	}

	public Double getOpen() {
		return open;
	}

	public Double getLow52() {
		return low52;
	}

	public Double getSecurityVar() {
		return securityVar;
	}

	public Double getPricebandupper() {
		return pricebandupper;
	}

	public Double getTotalTradedValue() {
		return totalTradedValue;
	}

	public Double getFaceValue() {
		return faceValue;
	}

	public Double getPreviousClose() {
		return previousClose;
	}

	public Double getVarMargin() {
		return varMargin;
	}

	public Double getLastPrice() {
		return lastPrice;
	}

	public Double getpChange() {
		return pChange;
	}

	public Double getAdhocMargin() {
		return adhocMargin;
	}

	public Double getAveragePrice() {
		return averagePrice;
	}

	public Double getIndexVar() {
		return indexVar;
	}

	public Double getPricebandlower() {
		return pricebandlower;
	}

	public Double getTotalBuyQuantity() {
		return totalBuyQuantity;
	}

	public Double getHigh52() {
		return high52;
	}

	public Double getClosePrice() {
		return closePrice;
	}

	public Boolean getIsExDateFlag() {
		return isExDateFlag;
	}

	public Double getTotalSellQuantity() {
		return totalSellQuantity;
	}

	public Double getDayHigh() {
		return dayHigh;
	}

	public Double getSellQuantity5() {
		return sellQuantity5;
	}

	public Double getSellQuantity2() {
		return sellQuantity2;
	}

	public Double getSellQuantity1() {
		return sellQuantity1;
	}

	public Double getBuyPrice1() {
		return buyPrice1;
	}

	public Double getSellQuantity4() {
		return sellQuantity4;
	}

	public Double getBuyPrice2() {
		return buyPrice2;
	}

	public Double getSellQuantity3() {
		return sellQuantity3;
	}

	public Double getApplicableMargin() {
		return applicableMargin;
	}

	public Double getBuyPrice4() {
		return buyPrice4;
	}

	public Double getBuyPrice3() {
		return buyPrice3;
	}

	public Double getBuyPrice5() {
		return buyPrice5;
	}

	public Double getDayLow() {
		return dayLow;
	}

	public Double getDeliveryToTradedQuantity() {
		return deliveryToTradedQuantity;
	}

	public Double getTotalTradedVolume() {
		return totalTradedVolume;
	}

	public void setLiveShareId(Long liveShareId) {
		this.liveShareId = liveShareId;
	}

	public void setSymbolid(CompanySymbol symbolid) {
		this.symbolid = symbolid;
	}

	public void setExtremeLossMargin(Double extremeLossMargin) {
		this.extremeLossMargin = extremeLossMargin;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public void setBcStartDate(Timestamp bcStartDate) {
		this.bcStartDate = bcStartDate;
	}

	public void setNdStartDate(Timestamp ndStartDate) {
		this.ndStartDate = ndStartDate;
	}

	public void setSecDate(Timestamp secDate) {
		this.secDate = secDate;
	}

	public void setCm_adj_low_dt(Timestamp cm_adj_low_dt) {
		this.cm_adj_low_dt = cm_adj_low_dt;
	}

	public void setRecordDate(Timestamp recordDate) {
		this.recordDate = recordDate;
	}

	public void setCm_adj_high_dt(Timestamp cm_adj_high_dt) {
		this.cm_adj_high_dt = cm_adj_high_dt;
	}

	public void setExDate(Timestamp exDate) {
		this.exDate = exDate;
	}

	public void setBcEndDate(Timestamp bcEndDate) {
		this.bcEndDate = bcEndDate;
	}

	public void setCss_status_desc(String css_status_desc) {
		this.css_status_desc = css_status_desc;
	}

	public void setNdEndDate(Timestamp ndEndDate) {
		this.ndEndDate = ndEndDate;
	}

	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public void setIsinCode(String isinCode) {
		this.isinCode = isinCode;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public void setCm_ffm(Double cm_ffm) {
		this.cm_ffm = cm_ffm;
	}

	public void setChange(Double change) {
		this.change = change;
	}

	public void setBuyQuantity3(Double buyQuantity3) {
		this.buyQuantity3 = buyQuantity3;
	}

	public void setSellPrice1(Double sellPrice1) {
		this.sellPrice1 = sellPrice1;
	}

	public void setBuyQuantity4(Double buyQuantity4) {
		this.buyQuantity4 = buyQuantity4;
	}

	public void setSellPrice2(Double sellPrice2) {
		this.sellPrice2 = sellPrice2;
	}

	public void setPriceBand(Double priceBand) {
		this.priceBand = priceBand;
	}

	public void setBuyQuantity1(Double buyQuantity1) {
		this.buyQuantity1 = buyQuantity1;
	}

	public void setDeliveryQuantity(Double deliveryQuantity) {
		this.deliveryQuantity = deliveryQuantity;
	}

	public void setBuyQuantity2(Double buyQuantity2) {
		this.buyQuantity2 = buyQuantity2;
	}

	public void setSellPrice5(Double sellPrice5) {
		this.sellPrice5 = sellPrice5;
	}

	public void setQuantityTraded(Double quantityTraded) {
		this.quantityTraded = quantityTraded;
	}

	public void setBuyQuantity5(Double buyQuantity5) {
		this.buyQuantity5 = buyQuantity5;
	}

	public void setSellPrice3(Double sellPrice3) {
		this.sellPrice3 = sellPrice3;
	}

	public void setSellPrice4(Double sellPrice4) {
		this.sellPrice4 = sellPrice4;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public void setLow52(Double low52) {
		this.low52 = low52;
	}

	public void setSecurityVar(Double securityVar) {
		this.securityVar = securityVar;
	}

	public void setPricebandupper(Double pricebandupper) {
		this.pricebandupper = pricebandupper;
	}

	public void setTotalTradedValue(Double totalTradedValue) {
		this.totalTradedValue = totalTradedValue;
	}

	public void setFaceValue(Double faceValue) {
		this.faceValue = faceValue;
	}

	public void setPreviousClose(Double previousClose) {
		this.previousClose = previousClose;
	}

	public void setVarMargin(Double varMargin) {
		this.varMargin = varMargin;
	}

	public void setLastPrice(Double lastPrice) {
		this.lastPrice = lastPrice;
	}

	public void setpChange(Double pChange) {
		this.pChange = pChange;
	}

	public void setAdhocMargin(Double adhocMargin) {
		this.adhocMargin = adhocMargin;
	}

	public void setAveragePrice(Double averagePrice) {
		this.averagePrice = averagePrice;
	}

	public void setIndexVar(Double indexVar) {
		this.indexVar = indexVar;
	}

	public void setPricebandlower(Double pricebandlower) {
		this.pricebandlower = pricebandlower;
	}

	public void setTotalBuyQuantity(Double totalBuyQuantity) {
		this.totalBuyQuantity = totalBuyQuantity;
	}

	public void setHigh52(Double high52) {
		this.high52 = high52;
	}

	public void setClosePrice(Double closePrice) {
		this.closePrice = closePrice;
	}

	public void setIsExDateFlag(Boolean isExDateFlag) {
		this.isExDateFlag = isExDateFlag;
	}

	public void setTotalSellQuantity(Double totalSellQuantity) {
		this.totalSellQuantity = totalSellQuantity;
	}

	public void setDayHigh(Double dayHigh) {
		this.dayHigh = dayHigh;
	}

	public void setSellQuantity5(Double sellQuantity5) {
		this.sellQuantity5 = sellQuantity5;
	}

	public void setSellQuantity2(Double sellQuantity2) {
		this.sellQuantity2 = sellQuantity2;
	}

	public void setSellQuantity1(Double sellQuantity1) {
		this.sellQuantity1 = sellQuantity1;
	}

	public void setBuyPrice1(Double buyPrice1) {
		this.buyPrice1 = buyPrice1;
	}

	public void setSellQuantity4(Double sellQuantity4) {
		this.sellQuantity4 = sellQuantity4;
	}

	public void setBuyPrice2(Double buyPrice2) {
		this.buyPrice2 = buyPrice2;
	}

	public void setSellQuantity3(Double sellQuantity3) {
		this.sellQuantity3 = sellQuantity3;
	}

	public void setApplicableMargin(Double applicableMargin) {
		this.applicableMargin = applicableMargin;
	}

	public void setBuyPrice4(Double buyPrice4) {
		this.buyPrice4 = buyPrice4;
	}

	public void setBuyPrice3(Double buyPrice3) {
		this.buyPrice3 = buyPrice3;
	}

	public void setBuyPrice5(Double buyPrice5) {
		this.buyPrice5 = buyPrice5;
	}

	public void setDayLow(Double dayLow) {
		this.dayLow = dayLow;
	}

	public void setDeliveryToTradedQuantity(Double deliveryToTradedQuantity) {
		this.deliveryToTradedQuantity = deliveryToTradedQuantity;
	}

	public void setTotalTradedVolume(Double totalTradedVolume) {
		this.totalTradedVolume = totalTradedVolume;
	}



	
	
}
