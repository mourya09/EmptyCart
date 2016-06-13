package com.cer.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cer.persistent.Product;
import com.cer.persistent.Seller;
import com.cer.services.SellerCatalogService;
import com.cer.services.SellerService;
import com.google.gson.Gson;

@Controller
public class SellerController {
	protected final Logger logger = LoggerFactory.getLogger(SellerController.class);

	@Autowired
	private SellerService sellerService;
	
	@Autowired
	private SellerCatalogService sellerCatalogService;

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

	@RequestMapping(value = "/getAllSeller", method = RequestMethod.POST/*
																			 * ,headers
																			 * =
																			 * "Content-Type=application/json"
																			 */)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getAllSeller() {
		String result = null;
		logger.info("getAllSeller Start");
		List<Seller> temp = sellerService.getSellerNearVicinity();

		if (!temp.isEmpty()) {
			Gson gson = new Gson();
			result = gson.toJson(temp, List.class);
		}

		logger.info("getAllSeller End");
		return result;
	}

	@RequestMapping(value = "/getSellerAddress", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getSellerAddress(@ModelAttribute Seller seller, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getSellerAddress Start");
		result = sellerService.getSellerAddress(seller.getLocationJson());

		logger.info("getSellerAddress End");
		return result;
	}
	
	@RequestMapping(value = "/getNearestSeller", method = RequestMethod.POST/*, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getNearestSeller(@ModelAttribute Seller seller, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getNearestSeller Start");
		result = sellerService.getNearestSeller(seller);
		logger.info("getNearestSeller End");
		return result;
	}
	
	@RequestMapping(value = "/getSellerWhoSellsTheProduct"/*, method = RequestMethod.POST, headers = "application/x-www-form-urlencoded; charset=UTF-8"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody String getSellerWhoSellsTheProduct(@ModelAttribute Product product, BindingResult bindingresult,
			HttpServletRequest request) {
		String result = null;
		logger.info("getSellerWhoSellsTheProduct Start");
		  List<FieldError> errors = bindingresult.getFieldErrors();
		    for (FieldError error : errors ) {
		    	logger.info(error.getObjectName() + " - " + error.getDefaultMessage());
		    }
		
		if(product.getName() == null || product.getName().equalsIgnoreCase(""))
		{
			product.setName(request.getParameter("name"));
		}
		List<Seller> sellerList = sellerCatalogService.getAllSeller(product.getName());
		if(!sellerList.isEmpty()){
			Gson gson = new Gson();
			result = gson.toJson(sellerList, List.class);
		}
		logger.info("getSellerWhoSellsTheProduct End");
		return result;
	}
	
	
	@ModelAttribute
    public Product getProduct(){
        

        return new Product();
    }
}
