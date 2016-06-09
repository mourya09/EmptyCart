package com.cer.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.DriverInformation;
import com.cer.services.DriverService;
@Service("driverService")
public class DriverServiceImpl implements DriverService {
	
	protected final Logger logger = LoggerFactory.getLogger(DriverServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	public boolean addDriverInformation(DriverInformation driver) {
		logger.info("addDriverInformation start");
		if(driver != null && driver.getName() != null )
		{
			gmDao.save(driver);
			if(driver.getDriverId() != null && driver.getDriverId() > 0		)
		{
				logger.info("addDriverInformation successfully saved");
				return true;
		}
		}
		logger.info("addDriverInformation Failed to saved");
		return false;
	}

	public boolean updateDriverInformation(DriverInformation driver) {
		if(driver != null && driver.getDriverId() != null )
		{
			gmDao.update(driver);
			logger.info("addDriverInformation successfully Updated");
				return true;
		}
		logger.info("addDriverInformation Failed to Update ");
		return false;
	}

	public boolean deleteDriverInformaiton(DriverInformation driver) {
		if(driver != null && driver.getDriverId() != null )
		{
			gmDao.delete(driver);
			logger.info("addDriverInformation successfully Deleted");
			return true;
		}
		
		logger.info("addDriverInformation Failed to Update");
		return false;
	}

}
