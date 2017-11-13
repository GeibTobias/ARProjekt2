package com.arvr.websocket;

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

import com.arvr.map.Map;

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
	
	@MessageMapping("/test")
	@SendTo("/map/test")
	@CrossOrigin
	public void testSocket(String msg) {
		
		log.info("Received test message: " + msg); 
		
		this.template.convertAndSend("/map/test", "Test message");
	}
}
