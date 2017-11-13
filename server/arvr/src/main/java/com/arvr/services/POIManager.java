package com.arvr.services;

import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.arvr.utils.ListManager;
import com.arvr.utils.POIEntry;

@RestController
@RequestMapping(path = "/poimanager/")
public class POIManager {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ListManager listManager; 
	
	@RequestMapping(path = "/add/{poi_id}", method = RequestMethod.PUT)
	public void addPOI(@PathVariable String poi_id) {
		
		log.info("Add POI to list: " + poi_id);
		
		POIEntry e = new POIEntry(); 
		e.poi_id = poi_id; 
		this.listManager.addPOI(e);
	}
	
	@RequestMapping(path = "/completelist", method = RequestMethod.GET)
	@Produces("application/json")
	public List<POIEntry> getCompleteList() {
		
		return this.listManager.getRouteAsList(); 
	}
	
	@RequestMapping(path = "/remove/{poi_id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable String poi_id) {
		
		log.info("Remove POI from list: " + poi_id);
		this.listManager.removePOI(poi_id);
	}
}
