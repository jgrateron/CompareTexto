package com.fresco;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFrame;

public class App extends JFrame{

	private static final long serialVersionUID = 1L;

	public void init() {
		setTitle("Comparar Textos");
		setSize(1400, 812);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		ComparePanel panel = new ComparePanel();
		add(panel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				super.windowOpened(e);
				panel.start();
			}
		});
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		var app = new App();
		app.init();
		app.setVisible(true);
	}

}
