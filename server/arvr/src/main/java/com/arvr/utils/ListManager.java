package com.arvr.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListManager {

	private HashMap<Long, POIEntry> route;
	
	public ListManager() {
		route = new HashMap<Long, POIEntry>(); 
	}
	
	public void addPOI(POIEntry poi) {
		
		if( poi == null )
			throw new IllegalArgumentException(); 
		
		route.put(poi.id, poi); 
	}
	
	public void removePOI(POIEntry poi) {
		
		if( poi == null )
			return; 
		
		removePOI(poi.id);
	}
	
	public void removePOI(long id) {
		
		route.remove(id); 
	}
	
	public List<POIEntry> getRouteAsList() {
		
		return new ArrayList<POIEntry>(route.values()); 
	}
	
	public HashMap<Long, POIEntry> getRouteAsMap() {
		
		return route; 
	}
}
