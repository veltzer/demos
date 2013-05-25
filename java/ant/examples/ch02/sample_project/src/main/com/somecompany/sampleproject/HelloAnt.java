package com.somecompany.sampleproject;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.Icon;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Container;

import java.util.Properties;

/**
 * This class is only used to demonstrate the capabilities of Jakarta Ant
 * capabilities.
 * @author Mark Veltzer <mark@veltzer.net>
 */
public abstract class HelloAnt {
	/**
	 * prints out a welcome message
	 */
	public static void main(String[] args) throws Exception {
		ClassLoader context = Thread.currentThread().getContextClassLoader();
		Properties props = new Properties();
		props.load(context.getResourceAsStream("conf/app.properties"));

		String msg = props.getProperty("welcome.message");
		ImageIcon icon = new ImageIcon(context.getResource("img/logo.gif"));
		new MyFrame(msg, icon).show();
	}

	static class MyFrame extends JFrame {
		MyFrame(String msg, Icon icon) {
			JLabel msgLabel = new JLabel(msg);
			msgLabel.setHorizontalAlignment(SwingConstants.CENTER);
			msgLabel.setFont(new Font("Times New Roman", Font.BOLD, 28));

			JLabel iconLabel = new JLabel(icon);
			iconLabel.setHorizontalAlignment(SwingConstants.CENTER);
			this.setLocation(100, 100);
			this.setSize(800, 400);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			Container content = this.getContentPane();
			content.setLayout(new BorderLayout());
			content.add(msgLabel, BorderLayout.NORTH);
			content.add(iconLabel, BorderLayout.CENTER);
		}
	}
}
