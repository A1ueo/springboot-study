package com.winter.app.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
	
	@GetMapping("/chat/room")
	public String room() {
		return "chat/chat";
	}

	@MessageMapping("/chat")	// /send
	@SendTo("/topic/messages")
	public String send(String message) {
		System.out.println(message);
		return message;
	}
}
