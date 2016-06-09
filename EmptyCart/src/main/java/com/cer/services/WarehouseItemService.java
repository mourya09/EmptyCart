package com.cer.services;

import com.cer.persistent.WarehouseItems;

public interface WarehouseItemService {
	public Boolean saveWarehouseItem(WarehouseItems items);
	public String getAllWarehouseItems(Long warehouseId);
	public String getItemsPresentInWarehouses(Long item);
	public Boolean deleteWarehouseItem(Long warehouseItemId);
	
	

}
