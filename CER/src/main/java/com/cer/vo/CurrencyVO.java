/**
 * 
 */
package com.cer.vo;

import com.cer.persistent.Currency;
import com.cer.persistent.CurrencyExchangeRate;

/**
 * @author Praveen Kumar
 * This class generated for providing all the information related with the currency.
 *
 */
public class CurrencyVO {

	private Currency currency;
	private CurrencyExchangeRate otherCurrencies;
	public String error;
	public Currency getCurrency() {
		return currency;
	}
	public CurrencyExchangeRate getOtherCurrencies() {
		return otherCurrencies;
	}
	public String getError() {
		return error;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	public void setOtherCurrencies(CurrencyExchangeRate otherCurrencies) {
		this.otherCurrencies = otherCurrencies;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
}
