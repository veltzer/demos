package com.nextgened.websockets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.jwebsocket.console.JWebSocketTokenListenerSample;
import org.jwebsocket.factory.JWebSocketFactory;
import org.jwebsocket.server.TokenServer;

/**
 * Application Lifecycle Listener implementation class JWebSocketServletContextListener
 *
 */
public class JWebSocketServletContextListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public JWebSocketServletContextListener() {
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        JWebSocketFactory.start();
        // Retrieve the TokenServer
        TokenServer lServer = (TokenServer)JWebSocketFactory.getServer("ts0");
        if( lServer != null ) {
          // Add the listener to the server's listener chain
          lServer.addListener(new ChatServerListener());
        }
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        JWebSocketFactory.stop();
    }
	
}
