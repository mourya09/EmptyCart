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

import com.cer.persistent.RouteTrackSystem;
import com.cer.services.RouteTrackingService;
import com.google.gson.Gson;

@Controller

public class RouteController {
	
	
	protected final Logger logger = LoggerFactory.getLogger(RouteController.class);
	
	@Autowired
	private RouteTrackingService routeTrackingService;
	
	@RequestMapping(value="/saveRoute",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  Boolean saveRoute(@ModelAttribute RouteTrackSystem routeTracking, BindingResult bindingresult,
			HttpServletRequest request)
	{
		Boolean result = false;
		logger.info("saveRoute Start");
		result = routeTrackingService.saveRoute(routeTracking);		
		logger.info("saveRoute End");
		return result;
	}
	
	
	@RequestMapping(value="/getSavedRoute",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  String getRoute(@ModelAttribute RouteTrackSystem routeTracking, BindingResult bindingresult,
			HttpServletRequest request)
	{
		String result = null;
		logger.info("getRoute Start");
		Long longId = routeTracking.getId();
		if (longId != null) {
			RouteTrackSystem temp = routeTrackingService.getRouteInGeoJSON(routeTracking.getId());
			if (temp != null) {
				Gson gson = new Gson();
				result = gson.toJson(temp, RouteTrackSystem.class);
			}
		}
		logger.info("getRoute End");
		return result;
	}
	
	
	@RequestMapping(value="/getAllSaveRoute",method=RequestMethod.POST/*,headers="Content-Type=application/json"*/)
	@ResponseStatus(HttpStatus.ACCEPTED)
	private @ResponseBody  String getRoute()
	{
		String result = null;
		logger.info("getAllSaveRoute Start");
		Gson gson = new Gson();
		
		
		List<RouteTrackSystem> temp = routeTrackingService.getAllRoute();
		if(! temp.isEmpty())
		{
			result = gson.toJson(temp, List.class);
		}
		
		logger.info("getAllSaveRoute End");
		return result;
	}
	
}
