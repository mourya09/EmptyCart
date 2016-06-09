/**
 * 
 */
package com.cer.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cer.persistent.UserManagement;
import com.cer.services.UserMgmtService;
import com.cer.util.PropertyConfigurer;

/**
 * @author Praveen Kumar
 * This controller will take care of the user login as well as the user roles.
 *
 */
@Controller
public class UserController {
	protected final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserMgmtService userMgmtService;
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;
	
	@RequestMapping("/")
	public ModelAndView login()
	{
		ModelAndView model=new ModelAndView("login");
		model.addObject("LIC1", propertyConfigurer.getProperty("MAP_API_KEY"));
		model.addObject("LIC2", propertyConfigurer.getProperty("GEOCODING_API_KEY"));
		
		return model;
	}
	
	/* @RequestMapping(value = "/", method = RequestMethod.GET)
	 public String handle(BindingResult result, RedirectAttributes redirectAttrs) {
		 return "index";
	 }*/
	
	@RequestMapping(value="/isAuthenticate",method=RequestMethod.POST,headers="Content-Type=application/json")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public UserManagement authenticateUser(@RequestBody UserManagement user, BindingResult bindingresult,
			HttpServletRequest request)
	{
		logger.info("authenticateUser start ");
		UserManagement result = userMgmtService.isAuthenticated(user.getUsername(), user.getPassword());
		logger.info("authenticateUser end ");
		return result;
	}
	
}
