package com.cer.services;

import java.util.List;

import com.cer.persistent.WareHouse;

public interface WarehouseService {
	
	
	
	public boolean saveWarehouse(WareHouse warehouse);
	
	
	public List<WareHouse> getWarehouseNearVicinity();
	
	public WareHouse getAWarehouse(Long warehouseId);
	
	public String getWareHouseAddress(String warehouse);
	
	public String getNearestWareHouse(WareHouse warehouse);

}
