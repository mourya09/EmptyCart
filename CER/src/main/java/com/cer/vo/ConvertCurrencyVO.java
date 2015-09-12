/**
 * 
 */
package com.cer.vo;

/**
 * @author Praveen Kumar
 * This class will collect the information regarding Currency Conversion.
 *
 */
public class ConvertCurrencyVO {
	private String convertFromCurrencyId;
	private String convertToCurrencyId;
	private Double currencyQty;
	private String error;
	public String getConvertFromCurrencyId() {
		return convertFromCurrencyId;
	}
	public String getConvertToCurrencyId() {
		return convertToCurrencyId;
	}
	public Double getCurrencyQty() {
		return currencyQty;
	}
	public String getError() {
		return error;
	}
	public void setConvertFromCurrencyId(String convertFromCurrencyId) {
		this.convertFromCurrencyId = convertFromCurrencyId;
	}
	public void setConvertToCurrencyId(String convertToCurrencyId) {
		this.convertToCurrencyId = convertToCurrencyId;
	}
	public void setCurrencyQty(Double currencyQty) {
		this.currencyQty = currencyQty;
	}
	public void setError(String error) {
		this.error = error;
	}
	

}
