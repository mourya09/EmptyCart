package com.cer.restful.services.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * This is the main controller which will expose all the webservices to any
 * client.
 */

@RestController
public class CurrencyExchangeController {
	protected final Logger logger = LoggerFactory.getLogger(CurrencyExchangeController.class);
	/*
	@Autowired
	private CERService cerService;
	@CrossOrigin
	@RequestMapping(value = "/GetAllCurrencyInformation", method = RequestMethod.POST)
	public @ResponseBody List<Currency> getAllCurrency() {
		logger.info("getAllCurrency start");
		List<Currency> list = null;
		list = cerService.getAllCurrency();
		logger.info("getAllCurrency end");
		return list;
	}
	@CrossOrigin
	@RequestMapping(value = "/GetACurrencyInformation", method = RequestMethod.POST)
	public @ResponseBody CurrencyVO getASpecificCurrency(@RequestBody(required=true) String currencyId) {
		logger.info("getASpecificCurrency start");
		CurrencyVO result = new CurrencyVO();
		String[] str = currencyId.split("=");
		if (currencyId == null ) {

			result.error = "Please provide Valid Currency Symbol";
			return result;
		}
		Long id = Long.parseLong(str[1]);
		Currency currency = cerService.getACurrency(id);
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
	@RequestMapping(value = "/GetAllCurrencyExchange", method = RequestMethod.POST)
	public @ResponseBody List<CurrencyExchangeRate> getAllCurrencyInformation() {
		logger.info("getAllCurrencyInformation start");
		
		List<CurrencyExchangeRate> list = cerService.getAllCurrencyExchange();

		logger.info("getAllCurrencyInformation end");
		return list;
	}
	@CrossOrigin
	@RequestMapping(value = "/CurrencyConvert", method = RequestMethod.POST)
	public @ResponseBody Double convertOneCurrencyToAnother(@RequestBody(required=true) String jsonData
			@RequestBody(required=true) ConvertCurrencyVO cCurrencyVO
			) {
		logger.info("convertOneCurrencyToAnother start");
		//Google Gson is not working as MediaType was not enabled in my CER.xml.
		//Due to timeconstraint, It can be fix later on.
		logger.info("Recieved JSON String is " + jsonData);	
		jsonData = jsonData.substring(0, jsonData.length() - 1);
		jsonData = URLDecoder.decode(jsonData) ;
		String[] str = jsonData.replace("{", "").replace("}", "").split("&");
		ConvertCurrencyVO cCurrencyVO = new ConvertCurrencyVO();
		if(str!= null && str[0] != null)
		{
			String[] temp =str[0].split("=");
			cCurrencyVO.setConvertFromCurrencyId(temp[1]);
			
		}
		if(str!= null && str[1] != null)
		{
			String[] temp =str[1].split("=");
			cCurrencyVO.setConvertToCurrencyId(temp[1]);
			
		}
		if(str!= null && str.length > 2&& str[2] != null)
		{
			String[] temp =str[2].split("=");
			cCurrencyVO.setCurrencyQty(Double.parseDouble(temp[1]));
			
		}
		Long currencyFrom = Long.parseLong(cCurrencyVO.getConvertFromCurrencyId());
		Long currencyTo=Long.parseLong(cCurrencyVO.getConvertToCurrencyId());
		CurrencyExchangeRate currencyExchange = cerService.getACurrencyExchangeRate(currencyFrom, currencyTo);
		Double result = null;
		if(cCurrencyVO.getCurrencyQty() == null || cCurrencyVO.getCurrencyQty() == 0)
		{
			result = currencyExchange.getConversationRate();
		}else
		{
			result = currencyExchange.getConversationRate() * cCurrencyVO.getCurrencyQty();
		}
		logger.info("convertOneCurrencyToAnother end");
		return result;
	}*/
}
