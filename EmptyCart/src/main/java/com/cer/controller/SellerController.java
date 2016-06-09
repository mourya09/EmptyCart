package com.cer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cer.persistent.Seller;
import com.cer.services.SellerService;
import com.google.gson.Gson;

@Controller
public class SellerController {
	protected final Logger logger = LoggerFactory.getLogger(SellerController.class);

	@Autowired
	private SellerService sellerService;

	@RequestMapping(value = "/saveWareHouse", method = RequestMethod.POST/*
																			 * ,headers
																			 * =
																			 * "Content-Type=application/json"
																			 */)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String saveWareHouse(@ModelAttribute Seller seller, BindingResult bindingresult,
			HttpServletRequest request) {
		Boolean result = false;
		String resultString = "null";
		logger.info("saveWareHouse Start");
		result = sellerService.saveSeller(seller);
		resultString = result.toString();
		logger.info("saveWareHouse End");
		return resultString;
	}

	@RequestMapping(value = "/getAllWareHouse", method = RequestMethod.POST/*
																			 * ,headers
																			 * =
																			 * "Content-Type=application/json"
																			 */)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getAllWareHouse() {
		String result = null;
		logger.info("getAllWareHouse Start");
		List<Seller> temp = sellerService.getSellerNearVicinity();

		if (!temp.isEmpty()) {
			Gson gson = new Gson();
			result = gson.toJson(temp, List.class);
		}

		logger.info("getAllWareHouse End");
		return result;
	}

	@RequestMapping(value = "/getWareHouseAddress", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getWareHouseAddress(@ModelAttribute Seller seller, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getWareHouseAddress Start");
		result = sellerService.getSellerAddress(seller.getLocationJson());

		logger.info("getWareHouseAddress End");
		return result;
	}
	
	@RequestMapping(value = "/getNearestWareHouse", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getNearestWareHouse(@ModelAttribute Seller seller, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getNearestWareHouse Start");
		result = sellerService.getNearestSeller(seller);
		logger.info("getNearestWareHouse End");
		return result;
	}
}
