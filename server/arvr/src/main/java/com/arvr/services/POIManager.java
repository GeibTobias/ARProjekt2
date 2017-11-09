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
	
	@RequestMapping(path = "/add/{id}", method = RequestMethod.PUT)
	public void addPOI(@PathVariable long id) {
		
		log.info("Add POI to list.");
		
		// do lookup for poi coords
		
		POIEntry e = new POIEntry(); 
		e.id = id; 
		this.listManager.addPOI(e);
	}
	
	@RequestMapping(path = "/completelist", method = RequestMethod.GET)
	@Produces("application/json")
	public List<POIEntry> getCompleteList() {
		
		return this.listManager.getRouteAsList(); 
	}
	
	@RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
	public void remove(@PathVariable long id) {
		
		log.info("Remove entry with id " + id + " from list.");
		this.listManager.removePOI(id);
	}
}
