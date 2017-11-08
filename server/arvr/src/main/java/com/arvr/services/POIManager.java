package com.arvr.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/poimanager/")
public class POIManager {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(path = "/add/{id}", method = RequestMethod.GET)
	public void addPOI(@PathVariable long id) {
		
		log.info("Add POI to list."); 
	}
}
