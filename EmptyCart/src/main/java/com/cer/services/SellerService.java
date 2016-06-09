package com.cer.services;

import java.util.List;

import com.cer.persistent.Seller;

public interface SellerService {
	
	
	
	public boolean saveSeller(Seller warehouse);
	
	
	public List<Seller> getSellerNearVicinity();
	
	public Seller getASeller(Long warehouseId);
	
	public String getSellerAddress(String warehouse);
	
	public String getNearestSeller(Seller warehouse);

}
