//package src;

import javax.swing.JFrame;


public class Window {

	private static Window window;
	private static Map map;
	
	public Window(){
		JFrame window = new JFrame();
		map = new Map();
		window.add(map);
		window.setTitle("Warring Summoners");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(800,450);
		window.setVisible(true);
		window.setLocationRelativeTo(null);
	}
	
	public static void main(String[] args){
		
		window = new Window();
		
	}
}
