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

import com.cer.persistent.Product;
import com.cer.services.ProductService;

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
	private ProductService productService;
	
	@Test
	public void addItemTest()
	{
		logger.info("addItemTest start ");
		Product product = new Product();
		product.setBrandName("Titan");
		product.setName("Tuffan");
		product.setWeight(50d);
		product.setQuantity(50l);
		product.setPrice(2100d);
		productService.saveItem(product);
		logger.info("addItemTest end ");
	}
	
	@Test
	public void getItemTest()
	{
		logger.info("getItemTest start ");
		Product product = productService.getItem(1l);
		Assert.assertNotNull(product);
		logger.info("getItemTest end ");
	}
	
	@Test
	public void getItemListTest()
	{
		logger.info("getItemListTest start ");
		List<Product> product = productService.getItem("Ladybug");
		Assert.assertNotNull(product);
		logger.info("getItemListTest end ");
	}
}
