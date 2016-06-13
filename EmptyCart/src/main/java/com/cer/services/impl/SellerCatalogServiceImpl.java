package com.cer.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cer.dao.GMDao;
import com.cer.persistent.Product;
import com.cer.persistent.Seller;
import com.cer.persistent.SellerCatalog;
import com.cer.services.ProductService;
import com.cer.services.SellerCatalogService;
import com.cer.services.SellerService;
import com.cer.util.GeoJsonReader;
import com.cer.util.GeoJsonWriter;
import com.cer.util.PropertyConfigurer;
import com.google.gson.Gson;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;

@Service("sellerCatalogService")
public class SellerCatalogServiceImpl implements SellerCatalogService {

	protected final Logger logger = LoggerFactory.getLogger(SellerServiceImpl.class);

	@Autowired
	private GMDao gmDao;

	@Autowired
	private SellerService sellerService;

	@Autowired
	private ProductService productService;

	private PrecisionModel precisionModel = new PrecisionModel(PrecisionModel.FLOATING);
	private GeometryFactory geomf = new GeometryFactory(precisionModel, 4326);
	private GeoJsonReader reader = new GeoJsonReader(geomf);
	private GeoJsonWriter writer = new GeoJsonWriter(14);

	@Autowired
	private PropertyConfigurer propertyConfigurer;

	public Boolean saveSellerCatalog(SellerCatalog items) {
		logger.info("saveWarehouseItem start ");
		Boolean result = false;
		Seller wh = sellerService.getASeller(items.getSellerId().getId());
		if (wh != null) {
			items.setSellerId(wh);
		}
		Product product = productService.getItem(items.getProductId().getId());
		if (product != null) {
			items.setProductId(product);
		}
		if (wh != null && product != null) {
			gmDao.save(items);
			if (items.getId() != null && items.getId() > 0) {
				result = true;
			}

		}
		logger.info("saveWarehouseItem end ");
		return result;
	}

	public String getAllSellerCatalog(Long warehouseId) {
		logger.info("getAllWarehouseItems start ");
		String result = null;
		if (warehouseId != null) {
			List<SellerCatalog> list = gmDao.find(propertyConfigurer.getProperty("GET_ALL_ITEMS_IN_A_WAREHOUSE"),
					warehouseId);
			if (list != null && !list.isEmpty()) {
				String geoJsonString = null;
				for (SellerCatalog item : list) {
					if (item.getSellerId() != null) {
						if (item.getSellerId().getLocation() != null) {
							geoJsonString = writer.write(item.getSellerId().getLocation());
							item.getSellerId().setLocationJson(geoJsonString);
							item.getSellerId().setLocation(null);
						}
						if (item.getSellerId().getServingArea() != null) {
							geoJsonString = writer.write(item.getSellerId().getServingArea());
							item.getSellerId().setServingAreaJson(geoJsonString);
							item.getSellerId().setServingArea(null);
						}

					}
				}
				Gson gson = new Gson();
				result = gson.toJson(list, List.class);

			}
		}

		logger.info("getAllWarehouseItems end ");
		return result;
	}

	public List<Seller> getAllSeller(String product) {
		logger.info("getAllSeller start");
		List<Seller> result = null;
		String sqlQuery = propertyConfigurer.getProperty("GET_PRODUCT_FOR_A_SELLER").replace("#",
				product.toLowerCase());
		result = gmDao.find(sqlQuery);
		if (result.isEmpty()) {
			logger.info("Not able to find the product in the SellerCatalog");
		} 

		logger.info("getAllSeller end");
		return result;
	}

	public String getProductPresentWithSeller(Long product) {
		logger.info("getItemsPresentInWarehouses start ");
		String result = null;
		if (product != null) {
			List<SellerCatalog> list = gmDao.find(propertyConfigurer.getProperty("GET_ALL_WAREHOUSE_FOR_A_ITEM"),
					product);
			if (list != null && !list.isEmpty()) {
				String geoJsonString = null;
				for (SellerCatalog item : list) {
					if (item.getSellerId() != null) {
						if (item.getSellerId().getLocation() != null) {
							geoJsonString = writer.write(item.getSellerId().getLocation());
							item.getSellerId().setLocationJson(geoJsonString);
							item.getSellerId().setLocation(null);
						}
						if (item.getSellerId().getServingArea() != null) {
							geoJsonString = writer.write(item.getSellerId().getServingArea());
							item.getSellerId().setServingAreaJson(geoJsonString);
							item.getSellerId().setServingArea(null);
						}

					}
				}
				Gson gson = new Gson();
				result = gson.toJson(list, List.class);

			}
		}

		logger.info("getItemsPresentInWarehouses end ");
		return result;
	}

	public Boolean deleteSellerCatalog(Long warehouseItemId) {
		logger.info("deleteWarehouseItem start");
		Boolean result = false;
		// GET_A_WAREHOUSE_ITEM_FOR_A_ITEM
		if (warehouseItemId != null && warehouseItemId > 0) {
			String sql = propertyConfigurer.getProperty("GET_A_WAREHOUSE_ITEM_FOR_A_ITEM");
			List<SellerCatalog> list = gmDao.find(sql, warehouseItemId);
			if (list != null && list.size() > 0) {
				SellerCatalog temp = list.get(0);
				gmDao.delete(temp);
				result = true;
			}
		}

		logger.info("deleteWarehouseItem end");
		return result;
	}

}
