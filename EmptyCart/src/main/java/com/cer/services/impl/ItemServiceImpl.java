package com.cer.services.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.Item;
import com.cer.services.ItemService;
import com.cer.util.PropertyConfigurer;
@Service("itemService")
public class ItemServiceImpl implements ItemService {

protected final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Autowired
	private GMDao gmDao;
	

	 @Autowired
	 private PropertyConfigurer propertyConfigurer;
	
	public Boolean saveItem(Item item) {
		logger.info("saveItem start");
		if(item != null && item.getName() != null )
		{
			gmDao.save(item);
			if(item.getId() != null && item.getId() > 0		)
		{
				logger.info("saveItem successfully saved");
				return true;
		}
		}
		logger.info("saveItem Failed to saved");
		return false;
	}

	public Item getItem(Long itemId) {
		logger.info("getItem start ");
		Item  result = null;
		List<Item> temp = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_A_ITEM");
		temp = gmDao.find(sqlQuery, itemId);
		if(!temp.isEmpty())
		{
			result = temp.get(0);
		}
		
		logger.info("getItem End ");
		return result;
	}
	
	public List<Item> getItem(String item)
	{
		logger.info("getItem String search Started ");
		List<Item> list = null;
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
