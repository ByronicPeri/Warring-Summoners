//package src;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



public class Map extends JPanel implements KeyListener, ActionListener {

	private Summoner[] hero;
	public static ArrayList<Summon>[] summons;
	private Image background;
	public static int rightBound, topBound, lowerBound, leftBound;
	
	//Timer time;
	//Thread draw;
	
	private int frameDelay = 40; // delay between repainting the window in milliseconds
	
	public Map() {
		hero = new Summoner[2];
		
		for(int i = 0; i < hero.length; i++){
			hero[i] = new Summoner();
			hero[i].setTeam(i);
		}
		
		summons = new ArrayList[2];
		for(int i = 0; i < summons.length; i++){
			summons[i] = new ArrayList<Summon>();
		}
		//summons.add(new Monster_1());
		
		
		setFocusable(true);
		
		// set map bounds
		rightBound = 800;
		leftBound = 0;
		topBound = 0;
		lowerBound = 300;
		
		this.addKeyListener(this);
		
		// import background image
		// this needs to be changed to a more general path
		ImageIcon i = new ImageIcon("src/Blue background.png");
		background = i.getImage();
		
		
		//time = new Timer(5, this);
		//time.start();
		
		new Timer(frameDelay, periodicRepaint).start();
	}
	
	ActionListener periodicRepaint = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    repaint();
		  }
	};
	
	@Override
	public void keyPressed(KeyEvent e) {
		for(int i = 0; i < hero.length; i++){
			hero[i].handleAction(e);
		}
		
		if(e.getKeyCode() == KeyEvent.VK_Z){
			for(int i = 0; i < summons[0].size(); i++){
				summons[0].get(i).changeAI();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_M){
			for(int i = 0; i < summons[1].size(); i++){
				summons[1].get(i).changeAI();
			}
		}
		// TODO Auto-generated method stub
		//System.out.println("pressed");
		//repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		for(int i = 0; i < hero.length; i++){
			hero[i].cancelAction(e);
		}
		System.out.println("released");
		//repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//System.out.println("typed");
		///repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		System.out.println("action");
		repaint();
	}
	
	public static void addSummon(int team, Summon newSummon){
		summons[team].add(newSummon);
	}
	
	public void update(){
		// update all units
		for(int i = 0; i < hero.length; i++){
			hero[i].update();
		}
		
		for(int j = 0; j < summons.length; j++){
			for(int i = 0; i < summons[j].size(); i++){
				summons[j].get(i).update(hero[j]);
			}
		}
		
		// Check for death
		for(int j = 0; j < summons.length; j++){
			for(int x = 0; x < summons[j].size(); x++){
				if(summons[j].get(x).isDead())
					summons[j].remove(x);
			}
		}
	}
	
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D graphics = (Graphics2D) g;
		
		//draw = new Thread(this);
		//draw.start();
		
		update();
		
		// draw background
		graphics.drawImage(background, 0, 0, null);
		
		// draw summons
		for(int j = 0; j < summons.length; j++){
			for(int i = 0; i < summons[j].size(); i++){
				graphics.drawImage(summons[j].get(i).getImage(), summons[j].get(i).getX(), summons[j].get(i).getY(), null);
			}
		}
		
		// draw player
		for(int i = 0; i < hero.length; i++){
			graphics.drawImage(hero[i].getImage(), hero[i].getX(), hero[i].getY(), null);
		}
	}

	/*
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}*/

	
}
