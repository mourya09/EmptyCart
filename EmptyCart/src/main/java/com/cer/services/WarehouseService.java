package com.cer.services;

import java.util.List;

import com.cer.persistent.Seller;

public interface WarehouseService {
	
	
	
	public boolean saveWarehouse(Seller warehouse);
	
	
	public List<Seller> getWarehouseNearVicinity();
	
	public Seller getAWarehouse(Long warehouseId);
	
	public String getWareHouseAddress(String warehouse);
	
	public String getNearestWareHouse(Seller warehouse);

}
