package com.cer.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.Product;
import com.cer.services.ProductService;
import com.cer.util.PropertyConfigurer;
@Service("productService")
public class ProductServiceImpl implements ProductService {

protected final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	

	 @Autowired
	 private PropertyConfigurer propertyConfigurer;
	
	public Boolean saveItem(Product product) {
		logger.info("saveItem start");
		if(product != null && product.getName() != null )
		{
			gmDao.save(product);
			if(product.getId() != null && product.getId() > 0		)
		{
				logger.info("saveItem successfully saved");
				return true;
		}
		}
		logger.info("saveItem Failed to saved");
		return false;
	}

	public Product getItem(Long itemId) {
		logger.info("getItem start ");
		Product  result = null;
		List<Product> temp = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_A_ITEM");
		temp = gmDao.find(sqlQuery, itemId);
		if(!temp.isEmpty())
		{
			result = temp.get(0);
		}
		
		logger.info("getItem End ");
		return result;
	}
	
	public List<Product> getItem(String item)
	{
		logger.info("getItem String search Started ");
		List<Product> list = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_STRING_ITEM").replaceAll("#", item.toLowerCase());
		list = gmDao.find(sqlQuery);
		if(list.isEmpty())
		{
			logger.error("Object not found!!!");
		}
		
		logger.info("getItem String search end ");
		return list;
	}
	

}
