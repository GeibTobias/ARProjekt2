package com.arvr.websocket;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.arvr.map.Coordinate;
import com.arvr.map.Map;
import com.arvr.utils.POIEntry;

@Controller
@EnableScheduling
public class MapUpdater {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private SimpMessagingTemplate template;
	
	public void updateMap() {
	
		new Thread(() -> {
		    this.sendUpdate(); 
		}).start();
	}
	
	
	@MessageMapping("/")
    @SendTo("/map/update")
	@CrossOrigin
	@Scheduled(fixedRate = 500)
	public void sendUpdate() {
		
		MapSettingUpdate msg = new MapSettingUpdate(Map.getCurrentMapFocus(), Map.getZoom()); 
		this.template.convertAndSend("/map/update", msg);
	}
	
	@MessageMapping("/setmap")
	@CrossOrigin
	public void getUpdate(MapSettingUpdate update) {
		
		log.info("Map update: ", update);
		
		if( update != null ) {
			Map.setZoom(update.getZoom());;
			Coordinate coords = update.getCoords(); 
			try {
				Map.setFocus(coords.lattitude, coords.longtitude);
			} catch (Exception e) {
				log.error("Can#t update map focus", e); 
			}
		}
	}
	
	@SendTo("/map/route/update")
	public void sendRouteListUpdate(List<POIEntry> route) {
		
		this.template.convertAndSend("/map/route/update", route);
	}
	
	@MessageMapping("/test")
	@SendTo("/map/test")
	@CrossOrigin
	public void testSocket(String msg) {
		
		log.info("Received test message: " + msg); 
		
		this.template.convertAndSend("/map/test", "Test message");
	}
}
