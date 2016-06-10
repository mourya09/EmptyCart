package com.cer.services;

import java.util.List;

import com.cer.persistent.Product;

public interface ProductService {
	public Boolean saveItem(Product product);

	public Product getItem(Long itemId);
	
	public List<Product> getItem(String item);

}
