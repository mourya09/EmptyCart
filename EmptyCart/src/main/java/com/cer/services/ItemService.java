package com.cer.services;

import java.util.List;

import com.cer.persistent.Item;

public interface ItemService {
	public Boolean saveItem(Item item);

	public Item getItem(Long itemId);
	
	public List<Item> getItem(String item);

}
