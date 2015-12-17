package com.cer.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cer.dao.DCMDao;
import com.cer.util.PropertyConfigurer;

public class DCMDaoImpl implements DCMDao {
	protected final Logger logger = LoggerFactory.getLogger(DCMDaoImpl.class);
	@Autowired
	private PropertyConfigurer propertyConfigurer;
	
	public Boolean restartTheDCMService() {
		logger.info("restartTheDCMService start");
		Boolean result = false;
		String serviceName = propertyConfigurer.getProperty("INTEL_SERVICE_NAME");
		if(serviceName != null && !"".equalsIgnoreCase(serviceName))
		{
			try
			{
				//String[] script = {"cmd.exe", "/c", "sc", "start", serviceName};
				String[] script = {"cmd.exe", "/c", "sc", "stop", serviceName};
				Runtime runTime = Runtime.getRuntime();
				Process process = runTime.exec(script);
				if(process.exitValue() == 0)
				{
					script[3] = "start";
					
					process = runTime.exec(script);
					result = true;
					
				}
				
			}catch(Exception e)
			{
				logger.error("Error in Service Restart ", e);
			}
		}
		logger.info("restartTheDCMService false");
		return result;
	}

}
