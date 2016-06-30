package com.cer.services;

import java.util.List;

import com.cer.persistent.Seller;
import com.cer.persistent.SellerCatalog;

public interface SellerCatalogService {
	public Boolean saveSellerCatalog(SellerCatalog sellerCatalog);
	public String getAllSellerCatalog(Long sellerId);
	public String getProductPresentWithSeller(Long product);
	public List<Seller> getAllSeller(String product, String lat, String lng);
	public String getAllSellerCoverage(String product );
	public Boolean deleteSellerCatalog(Long sellerCatalog);
	
	

}
