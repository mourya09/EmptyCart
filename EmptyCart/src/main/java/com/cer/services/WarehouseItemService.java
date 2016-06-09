package com.cer.services;

import com.cer.persistent.SellerCatalog;

public interface WarehouseItemService {
	public Boolean saveWarehouseItem(SellerCatalog items);
	public String getAllWarehouseItems(Long warehouseId);
	public String getItemsPresentInWarehouses(Long item);
	public Boolean deleteWarehouseItem(Long warehouseItemId);
	
	

}
