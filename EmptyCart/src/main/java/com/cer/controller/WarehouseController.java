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

import com.cer.persistent.WareHouse;
import com.cer.services.WarehouseService;
import com.google.gson.Gson;

@Controller
public class WarehouseController {
	protected final Logger logger = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	private WarehouseService warehouseService;

	@RequestMapping(value = "/saveWareHouse", method = RequestMethod.POST/*
																			 * ,headers
																			 * =
																			 * "Content-Type=application/json"
																			 */)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String saveWareHouse(@ModelAttribute WareHouse wareHouse, BindingResult bindingresult,
			HttpServletRequest request) {
		Boolean result = false;
		String resultString = "null";
		logger.info("saveWareHouse Start");
		result = warehouseService.saveWarehouse(wareHouse);
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
		List<WareHouse> temp = warehouseService.getWarehouseNearVicinity();

		if (!temp.isEmpty()) {
			Gson gson = new Gson();
			result = gson.toJson(temp, List.class);
		}

		logger.info("getAllWareHouse End");
		return result;
	}

	@RequestMapping(value = "/getWareHouseAddress", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getWareHouseAddress(@ModelAttribute WareHouse wareHouse, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getWareHouseAddress Start");
		result = warehouseService.getWareHouseAddress(wareHouse.getLocationJson());

		logger.info("getWareHouseAddress End");
		return result;
	}
	
	@RequestMapping(value = "/getNearestWareHouse", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getNearestWareHouse(@ModelAttribute WareHouse wareHouse, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getNearestWareHouse Start");
		result = warehouseService.getNearestWareHouse(wareHouse);
		logger.info("getNearestWareHouse End");
		return result;
	}
}
