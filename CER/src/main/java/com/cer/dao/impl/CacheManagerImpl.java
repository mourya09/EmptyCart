package com.cer.dao.impl;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.springframework.stereotype.Repository;

import com.cer.dao.CacheManager;
import com.cer.persistent.CompanySymbol;

@Repository
public class CacheManagerImpl implements CacheManager {

	private final Ignite ignite;
	
	public CacheManagerImpl(){
		ignite = Ignition.start("classpath:ignite-configuration.xml");
		initCaches();
	}
	private void initCaches() {
		  IgniteCache<Integer, CompanySymbol> igniteCache = ignite.getOrCreateCache(CompanySymbol.class.getName());
		 /* AlphaCache<Integer, CompanySymbol> ideaCache = new IgniteAlphaCache<>(igniteCache);
		  caches.put(BaseIdea.class, ideaCache);*/
		}
	public Object getCache(Object cacheClass) {
		// TODO Auto-generated method stub
		return null;
	}

}
