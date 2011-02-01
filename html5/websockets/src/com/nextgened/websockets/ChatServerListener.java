package com.nextgened.websockets;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.json.JSONException;
import org.json.JSONObject;
import org.jwebsocket.api.WebSocketPacket;
import org.jwebsocket.kit.WebSocketServerEvent;
import org.jwebsocket.listener.WebSocketServerTokenEvent;
import org.jwebsocket.listener.WebSocketServerTokenListener;
import org.jwebsocket.token.Token;

public class ChatServerListener implements WebSocketServerTokenListener {
	private DateFormat dateFormat;
	private static List<ChatMessage> messages;

	public static List<ChatMessage> getMessages() {
		return messages;
	}

	public boolean add(ChatMessage e) {
		return messages.add(e);
	}

	public ChatServerListener() {
		dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:MM:ss");
		messages = new Vector<ChatMessage>();
	}
	
	@Override
	public void processToken(WebSocketServerTokenEvent webSocketServerTokenEvent, Token token) {
		System.out.println("Got Token: " + token);
		JSONObject json = token.getJSONObject();
		ChatMessage chatMessage = jsonToMessage(json);
//		add(chatMessage);
		webSocketServerTokenEvent.broadcastToken(webSocketServerTokenEvent.getConnector(), token);
	}

	@Override
	public void processClosed(WebSocketServerEvent webSocketServerEvent) {
		System.out.println("Closed connection process, sessionId : " + webSocketServerEvent.getSessionId());
	}

	@Override
	public void processOpened(WebSocketServerEvent webSocketServerEvent) {
		System.out.println("Opened connection process, sessionId : " + webSocketServerEvent.getSessionId());
	}

	@Override
	public void processPacket(WebSocketServerEvent webSocketServerEvent, WebSocketPacket webSocketPacket) {
		// Low-level non-token packet processing
	}

	private ChatMessage jsonToMessage(JSONObject json) {
		ChatMessage chatMessage = new ChatMessage();
		User user = new User();
		try {
			user.setUserName(json.getString("user.userName"));
		} catch (JSONException e) {
			return null;
		}
		try {
			chatMessage.setDate(dateFormat.parse(json.getString("date")));
		} catch (Exception e) {
			chatMessage.setDate(new Date());
		}
		chatMessage.setUser(user);
		return chatMessage;
	}
}



