package com.cer.services;

import com.cer.persistent.Item;

public interface ItemService {
	public Boolean saveItem(Item item);
	public Item getItem(Long itemId);	

	
}
