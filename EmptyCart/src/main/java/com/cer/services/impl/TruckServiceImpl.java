package com.cer.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.TruckInformation;
import com.cer.services.TruckService;
@Service("truckService")
public class TruckServiceImpl implements TruckService {
	protected final Logger logger = LoggerFactory.getLogger(TruckServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	
	public boolean addTruck(TruckInformation truck) {
		logger.info("addDriverInformation start");
		if(truck != null && truck.getName() != null )
		{
			gmDao.save(truck);
			if(truck.getName() != null && truck.getName() != ""	)
		{
				logger.info("truck successfully saved");
				return true;
		}
		}
		logger.info("addDriverInformation Failed to saved");
		return false;
	}

	public boolean updateTruck(TruckInformation truck) {
		if(truck != null && truck.getId() != null )
		{
			gmDao.update(truck);
			logger.info("update successfully Updated");
				return true;
		}
		logger.info("addDriverInformation Failed to Update ");
		return false;
	}

	public boolean deleteTruck(TruckInformation truck) {
		if(truck != null && truck.getId() != null )
		{
			gmDao.delete(truck);
			logger.info("truck successfully Deleted");
			return true;
		}
		
		logger.info("addDriverInformation Failed to Update");
		return false;
	}

}
