package com.cer.services.impl;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.RouteTrackSystem;
import com.cer.services.RouteTrackingService;
import com.cer.util.GeoJsonReader;
import com.cer.util.GeoJsonWriter;
import com.cer.util.PropertyConfigurer;
import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;



@Service("routeTrackingService")
public class RouteTrackingServiceImpl implements RouteTrackingService {
	protected final Logger logger = LoggerFactory.getLogger(RouteTrackingServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	 private PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
		private GeometryFactory geomf = new GeometryFactory(precisionModel, 4326);
		private GeoJsonReader reader = new GeoJsonReader(geomf);
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;
	
	public Boolean saveRoute(RouteTrackSystem routeTracking) {
		logger.info("saveRoute start ");
		Boolean result = false;
		if (routeTracking != null && routeTracking.getRouteGeojson() != null && routeTracking.getDriverid() != null
				&& routeTracking.getTruckId() != null) {
			// First converting the geojson to Geometry
			if (routeTracking.getRoute() == null) {

				Geometry geom = null;

				if (routeTracking.getRouteGeojson() != null) {
					try {
						geom = reader.read(routeTracking.getRouteGeojson());
						
						Polygon poly = (Polygon) geom;
						if (poly != null) {
							routeTracking.setRoute(poly);

						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

			}

			gmDao.save(routeTracking);
			result = true;
		}

		logger.info("saveRoute end ");
		return result;
	}

	public RouteTrackSystem getRouteInGeoJSON(Long routeId) {
		logger.info("getRouteInGeoJSON start ");
		RouteTrackSystem result = null;
		if(routeId != null && routeId > 0)
		{
			try{
			List<RouteTrackSystem> list = gmDao.find(propertyConfigurer.getProperty("ROUTE_BY_ID"), routeId);
			if(list != null && list.size() > 0)
			{
				result = list.get(0);
				if(result.getRoute() != null)
				{
					GeoJsonWriter writer = new GeoJsonWriter(13);
					String tempString = writer.write(result.getRoute());
					if(tempString != null)
					{
						result.setRouteGeojson(tempString);
					}
					
					
				}
				
				
			}
			}catch(Exception ex)
			{ex.printStackTrace();}
		}
			
		logger.info("getRouteInGeoJSON end ");
		return result;
		
	}
	
	public List<RouteTrackSystem> getAllRoute()
	{
		logger.info("getRouteInGeoJSON start ");
		List<RouteTrackSystem> result = null;
		
			try{
				result = gmDao.find(propertyConfigurer.getProperty("GET_ALL_SAVED_ROUTE"));
				if(!result.isEmpty())
				{
					for(RouteTrackSystem temp: result)
					{
						if(temp.getRoute() != null)
						{
							GeoJsonWriter writer = new GeoJsonWriter(13);
							String tempString = writer.write(temp.getRoute());
							if(tempString != null)
							{
								temp.setRouteGeojson(tempString);
							}
							
							
						}
					}
				}
			
			}catch(Exception ex)
			{ex.printStackTrace();}
	
			
		logger.info("getRouteInGeoJSON end ");
		return result;
	}

}
