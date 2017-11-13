package com.arvr.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.arvr.map.Map;

@Controller
public class MapUpdater {

	@Autowired
    private SimpMessagingTemplate template;
	
	public void updateMap() {
	
		new Thread(() -> {
		    this.sendUpdate(); 
		}).start();
	}
	
	
	@MessageMapping("/")
    @SendTo("/map/update")
	public void sendUpdate() {
		
		MapSettingUpdate msg = new MapSettingUpdate(Map.getCurrentMapFocus(), Map.getZoom()); 
		this.template.convertAndSend("/topic/greetings", msg);
	}
	
	/*
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }
    */
}
