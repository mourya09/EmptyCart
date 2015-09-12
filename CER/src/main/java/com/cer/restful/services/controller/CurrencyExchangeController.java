package com.cer.restful.services.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cer.persistent.Currency;
import com.cer.persistent.CurrencyExchangeRate;
import com.cer.services.CERService;
import com.cer.vo.CurrencyVO;

/**
 * This is the main controller which will expose all the webservices to any
 * client.
 */

@RestController
public class CurrencyExchangeController {
	protected final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);

	@Autowired
	private CERService cerService;
	@CrossOrigin
	@RequestMapping(value = "/GetAllCurrencyInformation", method = RequestMethod.GET)
	public @ResponseBody List<Currency> getAllCurrency() {
		logger.info("getAllCurrency start");
		List<Currency> list = null;
		list = cerService.getAllCurrency();
		logger.info("getAllCurrency end");
		return list;
	}
	@CrossOrigin
	@RequestMapping(value = "/GetACurrencyInformation", method = RequestMethod.GET)
	public @ResponseBody CurrencyVO getASpecificCurrency(@PathVariable(value="currencySymbol") String currencySymbol) {
		logger.info("getASpecificCurrency start");
		CurrencyVO result = new CurrencyVO();
		if (currencySymbol == null || "".equalsIgnoreCase(currencySymbol)) {

			result.error = "Please provide Valid Currency Symbol";
			return result;
		}
		Currency currency = cerService.getACurrency(currencySymbol);
		if (currency == null) {

			result.error = "Provided Currency Symbol not found with us.";
		} else {
			result.setCurrency(currency);
			CurrencyExchangeRate cer = cerService.getACurrencyExchangeRate(currency.getCurrencyID());
			result.setOtherCurrencies(cer);
		}
		logger.info("getASpecificCurrency end");
		return result;
	}
	
	@CrossOrigin
	@RequestMapping(value = "/GetAllCurrencyExchange", method = RequestMethod.GET)
	public @ResponseBody List<CurrencyExchangeRate> getAllCurrencyInformation() {
		logger.info("getAllCurrencyInformation start");
		
		List<CurrencyExchangeRate> list = cerService.getAllCurrencyExchange();

		logger.info("getAllCurrencyInformation end");
		return list;
	}
	@CrossOrigin
	@RequestMapping(value = "/CurrencyConvert", method = RequestMethod.GET)
	public @ResponseBody Double convertOneCurrencyToAnother(@PathVariable(value="convertFromSymbol")
		String convertFromSymbol, @PathVariable(value="convertToSymbol") String convertToSymbol, @PathVariable(value="currencyQty")
		Double currencyQty
			) {
		logger.info("getAllCurrencyInformation start");
		
		CurrencyExchangeRate currencyExchange = cerService.getACurrencyExchangeRate(convertFromSymbol, convertToSymbol);
		Double result = null;
		if(currencyQty == null || currencyQty == 0)
		{
			result = currencyExchange.getConversationRate();
		}else
		{
			result = currencyExchange.getConversationRate() * currencyQty;
		}
		logger.info("getAllCurrencyInformation end");
		return result;
	}
}
