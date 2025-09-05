package com.winter.app.websocket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddWebsocketHandler implements WebSocketHandler {

	private List<WebSocketSession> users = new ArrayList<>();
	private Map<String, WebSocketSession> map = new HashMap<>();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		// websocket으로 연결 되었을 때 실행
		log.info("{}", session);

//		Principal principal = session.getPrincipal();
//		map.put(principal.getName(), session);
		users.add(session);
	}

	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		// 사용자가 메세지를 전송했을 때
		log.info("{}", message.getPayload());
		
		
		for (WebSocketSession s : users) {
			s.sendMessage(new TextMessage(session.getId().substring(0, 5) + ": " + message.getPayload()));
		}
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
		users.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		// 대용량
		return false;
	}

	
}
