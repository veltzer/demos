package com.nextgened.websockets;

import java.io.Serializable;
import java.util.Date;

public class ChatMessage implements Serializable {
	private static final long serialVersionUID = -116934283590018476L;
	private User user;
	private Date date;
	private String message;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatMessage [user=" + user + ", date=" + date + ", message=" + message + "]";
	}
}
