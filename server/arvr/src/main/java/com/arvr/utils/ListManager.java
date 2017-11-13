package com.arvr.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListManager {

	private HashMap<String, POIEntry> route;
	
	public ListManager() {
		route = new HashMap<String, POIEntry>(); 
	}
	
	public void addPOI(POIEntry poi) {
		
		if( poi == null )
			throw new IllegalArgumentException(); 
		
		route.put(poi.poi_id, poi); 
	}
	
	public void removePOI(POIEntry poi) {
		
		if( poi == null )
			return; 
		
		removePOI(poi.poi_id);
	}
	
	public void removePOI(String poi_id) {
		
		route.remove(poi_id); 
	}
	
	public List<POIEntry> getRouteAsList() {
		
		return new ArrayList<POIEntry>(route.values()); 
	}
	
	public HashMap<String, POIEntry> getRouteAsMap() {
		
		return route; 
	}
}
