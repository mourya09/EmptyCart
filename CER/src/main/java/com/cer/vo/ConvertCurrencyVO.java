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
	private String convertFromSymbol;
	private String convertToSymbol;
	private Double currencyQty;
	private String error;
	public String getConvertFromSymbol() {
		return convertFromSymbol;
	}
	public String getConvertToSymbol() {
		return convertToSymbol;
	}
	public Double getCurrencyQty() {
		return currencyQty;
	}
	public String getError() {
		return error;
	}
	public void setConvertFromSymbol(String convertFromSymbol) {
		this.convertFromSymbol = convertFromSymbol;
	}
	public void setConvertToSymbol(String convertToSymbol) {
		this.convertToSymbol = convertToSymbol;
	}
	public void setCurrencyQty(Double currencyQty) {
		this.currencyQty = currencyQty;
	}
	public void setError(String error) {
		this.error = error;
	}
	

}
