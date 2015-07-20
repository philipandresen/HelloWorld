package main;


import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Window{


	public Window(int width, int height, String title, HelloWorld game){
		JFrame frame = new JFrame(title);
		
		frame.setPreferredSize(new Dimension(width, height));
		frame.setMaximumSize(new Dimension(width, height));
		frame.setMinimumSize(new Dimension(width, height));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		JOptionPane.showMessageDialog(frame, "WELCOME TO PHILIP'S AWESOME GAME. USE THE ARROW KEYS TO MOVE.");
		game.start();
	}
}
