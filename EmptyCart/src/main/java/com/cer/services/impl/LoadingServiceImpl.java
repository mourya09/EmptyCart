package com.cer.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.LoadingUnloading;
import com.cer.persistent.TruckInformation;
import com.cer.services.LoadingService;
import com.cer.util.GeoJsonReader;
import com.cer.util.GeoJsonWriter;
import com.cer.util.PropertyConfigurer;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
@Service("loadingService")
public class LoadingServiceImpl implements LoadingService {

	
protected final Logger logger = LoggerFactory.getLogger(LoadingServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	 @Autowired
	 private PropertyConfigurer propertyConfigurer;
	 
	 
	 private PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
		private GeometryFactory geomf = new GeometryFactory(precisionModel, 4326);
		private GeoJsonReader reader = new GeoJsonReader(geomf);
		private GeoJsonWriter writer = new GeoJsonWriter(14);
		
	
	public Boolean saveLoading(LoadingUnloading loadInformation) {
		logger.info("saveWarehouse start ");
		boolean result = false;
		try
		{
			if(loadInformation != null && loadInformation.getLocationJson() != null )
			{
				try {
					
					
					Geometry geom = null;
					
					if(loadInformation.getLocationJson() != null && !"".equalsIgnoreCase(loadInformation.getLocationJson()))
					{
						geom = reader.read(loadInformation.getLocationJson());
						
						if (geom.getGeometryType().equalsIgnoreCase("Point") && geom != null) {
							Point location = (Point)geom;
							loadInformation.setLocation(location);
						}
					}
					if(loadInformation.getTruckId() != null )
					{
						List<TruckInformation> list = gmDao.find(propertyConfigurer.getProperty(""), loadInformation.getTruckId().getId());
						if(list != null)
						{
							loadInformation.setTruckId(list.get(0));
						}
					}

				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				gmDao.save(loadInformation);
				if(loadInformation.getId() != null && loadInformation.getId() > 0)
				{
					result = true;
				}
				
			}
			
		}catch(Exception ex)
		{
			ex.printStackTrace();
			result = false;
		}
		
		logger.info("saveWarehouse End");
		return result;
	}

	public List<LoadingUnloading> getAllLoadedInformation() {
		logger.info("getAllLoadedInformation start");
		List<LoadingUnloading> result = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_LOADING_UNLOADING_INFORMATION");
		result = gmDao.find(sqlQuery);
		if(result.isEmpty())
		{
			logger.info("No Loading/Unloading information is present!!!!");
		}else
		{
			for(LoadingUnloading temp:result)
			{
				if(temp.getLocation() != null)
				{
					String locationGeoJsonString = writer.write(temp.getLocation());
					temp.setLocationJson(locationGeoJsonString);
				}
			}
		}
		
		logger.info("getAllLoadedInformation end");
		return result;
	}

	public List<LoadingUnloading> getAllTruckLoadedInformaton(Long truckId) {
		logger.info("getAllTruckLoadedInformaton start");
		List<LoadingUnloading> result = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_LOADING_UNLOADING_INFORMATION_FOR_A_TRUCK");
		result = gmDao.find(sqlQuery, truckId);
		if(result.isEmpty())
		{
			logger.info("No Loading/Unloading information is present!!!!");
		}else
		{
			for(LoadingUnloading temp:result)
			{
				if(temp.getLocation() != null)
				{
					String locationGeoJsonString = writer.write(temp.getLocation());
					temp.setLocationJson(locationGeoJsonString);
				}
			}
		}
		
		logger.info("getAllTruckLoadedInformaton end");
		return result;
	}

	public List<LoadingUnloading> getLoadingInformation(Long loadingId) {
		logger.info("getLoadingInformation start");
		List<LoadingUnloading> result = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_LOADING_UNLOADING_INFORMATION_FOR_A_LOCATION");
		result = gmDao.find(sqlQuery, loadingId);
		if(result.isEmpty())
		{
			logger.info("No Loading/Unloading information is present!!!!");
		}else
		{
			for(LoadingUnloading temp:result)
			{
				if(temp.getLocation() != null)
				{
					String locationGeoJsonString = writer.write(temp.getLocation());
					temp.setLocationJson(locationGeoJsonString);
				}
			}
		}
		
		logger.info("getLoadingInformation end");
		return result;
	}

}
