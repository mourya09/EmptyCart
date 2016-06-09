package com.cer.services;

import java.util.List;

import com.cer.persistent.RouteTrackSystem;

public interface RouteTrackingService {

	public Boolean saveRoute(RouteTrackSystem routeTracking);
	
	public RouteTrackSystem getRouteInGeoJSON(Long routeId);
	
	public List<RouteTrackSystem> getAllRoute();
	
	
}
