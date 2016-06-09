/**
 * 
 */
package com.cer.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.cer.persistent.Item;
import com.cer.services.ItemService;

/**
 * @author Praveen Kumar
 *
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:EmptyCart.xml" })
@TransactionConfiguration(transactionManager = "gmTransactionManager", defaultRollback = false)
@Transactional
public class ItemTest {
	protected final Logger logger = LoggerFactory.getLogger(ItemTest.class);

	@Autowired
	private ItemService itemService;
	
	@Test
	public void addItemTest()
	{
		logger.info("addItemTest start ");
		Item item = new Item();
		item.setBrandName("Titan");
		item.setName("Tuffan");
		item.setWeight(50d);
		item.setQuantity(50l);
		item.setPrice(2100d);
		itemService.saveItem(item);
		logger.info("addItemTest end ");
	}
	
	@Test
	public void getItemTest()
	{
		logger.info("getItemTest start ");
		Item item = itemService.getItem(1l);
		Assert.assertNotNull(item);
		logger.info("getItemTest end ");
	}
	
	@Test
	public void getItemListTest()
	{
		logger.info("getItemListTest start ");
		List<Item> item = itemService.getItem("Ladybug");
		Assert.assertNotNull(item);
		logger.info("getItemListTest end ");
	}
}
