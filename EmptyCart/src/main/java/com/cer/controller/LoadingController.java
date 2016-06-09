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

import com.cer.persistent.LoadingUnloading;
import com.cer.services.LoadingService;
import com.google.gson.Gson;

@Controller
public class LoadingController {
	
	protected final Logger logger = LoggerFactory.getLogger(LoadingController.class);
	
	
	@Autowired
	private LoadingService loadingService;
	
	@RequestMapping(value="/saveLoadingUnloading",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  Boolean saveLoadingUnloading(@ModelAttribute LoadingUnloading load, BindingResult bindingresult,
			HttpServletRequest request)
	{
		Boolean result = false;
		logger.info("saveLoadingUnloading Start");
		result = loadingService.saveLoading(load);
		logger.info("saveLoadingUnloading End");
		return result;
	}
	
	
	@RequestMapping(value="/getAllLoaded",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  String getAllLoadingUnloadingInformation()
	{
		String result = null;
		logger.info("getAllLoadingUnloadingInformation Start");
		
		
			List<LoadingUnloading> temp = loadingService.getAllLoadedInformation();
			if (temp != null) {
				Gson gson = new Gson();
				result = gson.toJson(temp, List.class);
			}
		
		logger.info("getAllLoadingUnloadingInformation End");
		return result;
	}
	
	
	@RequestMapping(value="/getTruckLoadedInformation",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  String getTruckLoadedInformation(@ModelAttribute Long truckId, BindingResult bindingresult,
			HttpServletRequest request)
	{
		String result = null;
		logger.info("getTruckLoadedInformation Start");
		
		
			List<LoadingUnloading> temp = loadingService.getAllTruckLoadedInformaton(truckId);
			if (temp != null) {
				Gson gson = new Gson();
				result = gson.toJson(temp, List.class);
			}
		
		logger.info("getTruckLoadedInformation End");
		return result;
	}
	
	@RequestMapping(value="/getALoadedInformation",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  String getALoadedInformation(@ModelAttribute Long loadingId, BindingResult bindingresult,
			HttpServletRequest request)
	{
		String result = null;
		logger.info("getALoadedInformation Start");
		
		
			List<LoadingUnloading> temp = loadingService.getLoadingInformation(loadingId);
			if (temp != null) {
				Gson gson = new Gson();
				result = gson.toJson(temp, List.class);
			}
		
		logger.info("getALoadedInformation End");
		return result;
	}

}
