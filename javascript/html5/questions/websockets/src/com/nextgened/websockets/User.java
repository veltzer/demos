package com.nextgened.websockets;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6553913744959803509L;
		private String userName, sessionId;

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getSessionId() {
			return sessionId;
		}

		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}

		@Override
		public String toString() {
			return "User [userName=" + userName + ", sessionId=" + sessionId + "]";
		}
	}