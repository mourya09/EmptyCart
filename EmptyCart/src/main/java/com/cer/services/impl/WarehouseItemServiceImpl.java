package com.cer.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.Item;
import com.cer.persistent.WareHouse;
import com.cer.persistent.WarehouseItems;
import com.cer.services.ItemService;
import com.cer.services.WarehouseItemService;
import com.cer.services.WarehouseService;
import com.cer.util.GeoJsonReader;
import com.cer.util.GeoJsonWriter;
import com.cer.util.PropertyConfigurer;
import com.google.gson.Gson;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@Service("warehouseItemService")
public class WarehouseItemServiceImpl implements WarehouseItemService {
	
protected final Logger logger = LoggerFactory.getLogger(WarehouseServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	
	@Autowired
	private WarehouseService warehouseService;
	
	@Autowired
	private ItemService itemService;
	
	 private PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
		private GeometryFactory geomf = new GeometryFactory(precisionModel, 4326);
		private GeoJsonReader reader = new GeoJsonReader(geomf);
		private GeoJsonWriter writer = new GeoJsonWriter(14);
	
	@Autowired
	private PropertyConfigurer propertyConfigurer;

	public Boolean saveWarehouseItem(WarehouseItems items) {
		logger.info("saveWarehouseItem start ");
		Boolean result = false;
		WareHouse wh = warehouseService.getAWarehouse(items.getWhid().getId());
		if(wh != null )
		{
			items.setWhid(wh);
		}
		Item item = itemService.getItem(items.getItemid().getId());
		if(item != null)
		{
			items.setItemid(item);
		}
		if(wh != null && item != null)
		{
			gmDao.save(items);
			if(items.getId() != null && items.getId() > 0 )
			{
				result = true;
			}
			
		}
		logger.info("saveWarehouseItem end ");
		return result;
	}

	public String getAllWarehouseItems(Long warehouseId) {
		logger.info("getAllWarehouseItems start ");
		String result = null;
		if (warehouseId != null) {
			List<WarehouseItems> list = gmDao.find(propertyConfigurer.getProperty("GET_ALL_ITEMS_IN_A_WAREHOUSE"),
					warehouseId);
			if (list != null && !list.isEmpty()) {
				String geoJsonString = null;
				for (WarehouseItems item : list) {
					if (item.getWhid() != null) {
						if (item.getWhid().getLocation() != null) {
							geoJsonString = writer.write(item.getWhid().getLocation());
							item.getWhid().setLocationJson(geoJsonString);
							item.getWhid().setLocation(null);
						}
						if (item.getWhid().getServingArea() != null) {
							geoJsonString = writer.write(item.getWhid().getServingArea());
							item.getWhid().setServingAreaJson(geoJsonString);
							item.getWhid().setServingArea(null);
						}

					}
				}
				Gson gson = new Gson();
				result = gson.toJson(list, List.class);
				
			}
		}

		logger.info("getAllWarehouseItems end ");
		return result;
	}

	public String getItemsPresentInWarehouses(Long items) {
		logger.info("getItemsPresentInWarehouses start ");
		String result = null;
		if (items != null) {
			List<WarehouseItems> list = gmDao.find(propertyConfigurer.getProperty("GET_ALL_WAREHOUSE_FOR_A_ITEM"),
					items);
			if (list != null && !list.isEmpty()) {
				String geoJsonString = null;
				for (WarehouseItems item : list) {
					if (item.getWhid() != null) {
						if (item.getWhid().getLocation() != null) {
							geoJsonString = writer.write(item.getWhid().getLocation());
							item.getWhid().setLocationJson(geoJsonString);
							item.getWhid().setLocation(null);
						}
						if (item.getWhid().getServingArea() != null) {
							geoJsonString = writer.write(item.getWhid().getServingArea());
							item.getWhid().setServingAreaJson(geoJsonString);
							item.getWhid().setServingArea(null);
						}

					}
				}
				Gson gson = new Gson();
				result = gson.toJson(list, List.class);
				
			}
		}

		logger.info("getItemsPresentInWarehouses end ");
		return result;
	}

	public Boolean deleteWarehouseItem(Long warehouseItemId) {
		logger.info("deleteWarehouseItem start");
		Boolean result = false;
		//GET_A_WAREHOUSE_ITEM_FOR_A_ITEM
		if(warehouseItemId != null && warehouseItemId > 0)
		{
			String sql = propertyConfigurer.getProperty("GET_A_WAREHOUSE_ITEM_FOR_A_ITEM");
			List<WarehouseItems> list= gmDao.find(sql, warehouseItemId);
			if(list != null && list.size() > 0)
			{
				WarehouseItems temp = list.get(0);
				gmDao.delete(temp);
				result = true;
			}
		}
		
		logger.info("deleteWarehouseItem end");
		return result;
	}

}
