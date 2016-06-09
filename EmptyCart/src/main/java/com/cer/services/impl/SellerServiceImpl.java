package com.cer.services.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.cer.dao.GMDao;
import com.cer.persistent.Seller;
import com.cer.services.SellerService;
import com.cer.util.GeoJsonConstants;
import com.cer.util.GeoJsonReader;
import com.cer.util.GeoJsonWriter;
import com.cer.util.PropertyConfigurer;
import com.google.gson.Gson;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;



@Service("sellerService")
public class SellerServiceImpl implements SellerService {
protected final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	 private PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
		private GeometryFactory geomf = new GeometryFactory(precisionModel, 4326);
		private GeoJsonReader reader = new GeoJsonReader(geomf);
		private GeoJsonWriter writer = new GeoJsonWriter(14);
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;
	
	public boolean saveSeller(Seller warehouse) {
		logger.info("saveWarehouse start ");
		boolean result = false;
		if(warehouse != null )
		{
			Geometry geom = null;
			
			if(warehouse.getLocationJson() != null && !"".equalsIgnoreCase(warehouse.getLocationJson()))
			{
				try {
					geom = reader.read(warehouse.getLocationJson());
					if (geom.getGeometryType().equalsIgnoreCase(GeoJsonConstants.NAME_POINT) && geom != null) {
						Point location = (Point)geom;
						warehouse.setLocation(location);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			if(warehouse.getServingAreaJson() != null && !"".equalsIgnoreCase(warehouse.getServingAreaJson()))
			{
				try {
					geom = reader.read(warehouse.getServingAreaJson());
					if (geom.getGeometryType().equalsIgnoreCase(GeoJsonConstants.NAME_POLYGON) && geom != null) {
						Polygon location = (Polygon)geom;
						warehouse.setServingArea(location);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			if(warehouse.getLocation() != null && warehouse.getServingArea() != null)
			{
				gmDao.save(warehouse);
				result = true;
			}
		}
		
		logger.info("saveWarehouse end ");
		
		return result;
	}

	public List<Seller> getSellerNearVicinity() {
		logger.info("getWarehouseNearVicinity start ");
		List<Seller> result = null;
		
			Geometry geom = null;
			String sqlQuery = propertyConfigurer.getProperty("GET_NEAREST_WAREHOUSE");
			
				try {
					result = gmDao.find(sqlQuery);
					String jsonString = null;
					for(Seller warehouse: result)
					{
						if(warehouse.getLocation() != null)
						{
							jsonString = writer.write(warehouse.getLocation());
							warehouse.setLocationJson(jsonString);
							warehouse.setLocation(null);
						}
						if(warehouse.getServingArea() != null)
						{
							jsonString = writer.write(warehouse.getServingArea());
							warehouse.setServingAreaJson(jsonString);
							warehouse.setServingArea(null);
						}
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			
			
			
		
		logger.info("getWarehouseNearVicinity end ");
		
		return result;
	}
	
	
	public Seller getASeller(Long warehouseId)
	{
		logger.info("getAWarehouse start ");
		Seller result = null;
		List<Seller> warehouseList = gmDao.find(propertyConfigurer.getProperty("GET_A_WAREHOUSE"), warehouseId);
		if(warehouseList != null && warehouseList.size() > 0)
		{
			result = warehouseList.get(0);
		}
		logger.info("getAWarehouse end");
		return result;
	}
	
	public String getNearestSeller(Seller warehouse)
 {
		logger.info("getNearestWareHouse start");
		String result = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_ALL_WITHIN_A_CERTAIN_DISTANCE")
				.replace("?", warehouse.getLat()).replace("#", warehouse.getLng()).replace("@", warehouse.getPLZ());
		logger.info("Created Query is " + sqlQuery);
		try {
			List<Seller> list = gmDao.nearestWarehouse(sqlQuery);
			if(list != null && list.size() > 0)
			{
				Gson gson = new Gson();
				result = gson.toJson(list, List.class);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("getNearestWareHouse end");

		return result;
	}
	
	public String getSellerAddress(String warehouse)
	{
		logger.info("getWareHouseAddress start ");
		String result = "null";
		if (warehouse != null && !"".equalsIgnoreCase(warehouse)) {
			String[] temp = warehouse.split(";");
			if (temp.length > 0) {
				String url = propertyConfigurer.getProperty("REVERSE_GEOCODING_URL").trim() + "&lic_key="
						+ propertyConfigurer.getProperty("GEOCODING_API_KEY").trim() + "&lng=" + temp[0]+"&lat=" + temp[1];

				/*RestTemplate restTemplate = new RestTemplate();*/
				HttpClient client = new HttpClient();
				HttpMethod method = new GetMethod(url);
				
					
					 method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, 
					    		new DefaultHttpMethodRetryHandler(3, false));
					 
					 try{
						 int statusCode = client.executeMethod(method);
						 if( statusCode >= 300)
						 {
							 logger.info("Not able to execute the method or some error");
							 return result;
						 }
					
				/*ResponseEntity<Object> response = restTemplate.getForEntity(url,
						Object.class);*/

				/*if (response != null && response.getBody() != null) {*/
						 byte[] responseBody = method.getResponseBody();
						
						result = new String(responseBody);
				/*}*/
					
				}/*catch(RestClientException ex)
				{
					ex.printStackTrace();
				
				}*/catch(Exception ex)
				{
					ex.printStackTrace();
				}finally{
					method.releaseConnection();
				}
			}
		}
		logger.info("getWareHouseAddress end ");
		return result;

	}

}
