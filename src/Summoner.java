//package src;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;


public class Summoner {

	private int x, y, dy, speed, jumpstrength, gravity, health, mana, maxHealth, maxMana, manaRegen;
	private Image img;
	boolean left, right, jump;
	
	private int playerSize = 50;
	
	private int team;
	
	private int[] _move_right = { KeyEvent.VK_D,  KeyEvent.VK_L};
	private int[] _move_left = {KeyEvent.VK_A, KeyEvent.VK_J};
	private int[] _move_jump = {KeyEvent.VK_W, KeyEvent.VK_I};
	private int[][] _spells = { {KeyEvent.VK_1, KeyEvent.VK_2, KeyEvent.VK_3}, {KeyEvent.VK_8, KeyEvent.VK_9, KeyEvent.VK_0} };
	
	public Summoner(){
		x = 0; // initial x position
		y = 300; // initial y position
		dy = 0; // change in y position
		maxHealth = 100;
		maxMana = 100;
		manaRegen = 1;
		health = maxHealth; // starting health = maximum health
		mana = 0; // start with 0 mana
		jumpstrength = 20;
		gravity = 1; // pull of gravity
		speed = 10; // set movement speed
		
		team = 0;
		
		// this needs to be changed to a more general path
		ImageIcon i = new ImageIcon("src/player.png");
		img = i.getImage();
		
	}
	
	public void setTeam(int i){
		team = i;
	}
	
	// handle ActionEvents for character
	public void handleAction(KeyEvent e){
		if(e.getKeyCode() == _move_right[team]){
			right = true;
			//System.out.println("left");
		}
		else if(e.getKeyCode() ==_move_left[team]){
			left = true;
			//System.out.println("right");
		}
		else if(e.getKeyCode() == _move_jump[team]){
			jump = true;
		}
		
	}
	
	public void cancelAction(KeyEvent e){
		if(e.getKeyCode() == _move_right[team]){
			right = false;
		}
		else if(e.getKeyCode() == _move_left[team]){
			left = false;
		}
		else if(e.getKeyCode() == _move_jump[team]){
			jump = false;
		}
		// cast spells
		else if(e.getKeyCode() == _spells[team][0]){
			if(mana >= Monster_1.manaCost){
				Summon newSummon = new Monster_1(team);
				newSummon.setX(x);
				Map.addSummon(team, newSummon);
				mana -= Monster_1.manaCost;
			}
		}
	}
	
	// note that this will be called many times a second.  mana and manaRegen should probably be changed to doubles
	public void update(){
		
		if(right == true){
			if( x < Map.rightBound - playerSize){
				x += speed;
			}
			//System.out.println("left");
		}
		else if(left == true){
			if( x > 0){
				x -= speed;
			}
			//System.out.println("right");
		}
		if(jump == true){
			if(y >= 300){
				dy = jumpstrength;
			}
		}
		
		if(y < 300){
			y -= dy;
		}
		else if (y == 300 && dy > 0){
			y -= dy;
		}
		else{
			if(y > 300){
				y = 300;
			}
			
			dy = 0;
		}
		
		dy -= gravity;
		
		
		//x += speed;
		
		if(mana < maxMana){
			mana += manaRegen;
		}
		//System.out.println("update Hero");
		
	}
	
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return img;
	}
}
