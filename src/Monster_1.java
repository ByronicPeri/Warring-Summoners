//package src;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;



public class Monster_1 implements Summon {

	private int x = 0;
	private int y = 300;
	private int summonSize = 50;
	private int hp;
	private int maxHP = 100;
	private int speed = 2;
	private boolean goRight;
	private int attackDamage = 5;
	private int attackDelay = 10;
	private int team;
	private boolean dead = false;
	public static int manaCost = 10;
	private int range = 20;
	
	private int ai;
	private final static int ATTACK = 0;
	private final static int GUARD = 1;
	private final static int FOLLOW = 2;
	
	private boolean stop;
	
	private Image img;
	
	private int attackDelayCounter = 0;
	
	public Monster_1(){
		hp = maxHP;
		goRight = true;
		team = 1;
		
		ai = 2;
		stop = false;

		ImageIcon i = new ImageIcon("src/player.png");
		img = i.getImage();
	}
	
	public Monster_1(int team){
		hp = maxHP;
		goRight = true;
		this.team = team;
		
		ImageIcon i = new ImageIcon("src/player.png");
		img = i.getImage();
	}
	
	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public int getSpeed() {
		return speed;
	}

	@Override
	public int getHP() {
		return hp;
	}

	@Override
	public int getMaxHP() {
		return maxHP;
	}

	@Override
	public int getAttackDamage() {
		return attackDamage;
	}

	@Override
	public int getAttackDelay() {
		return attackDelay;
	}

	@Override
	public int getTeam() {
		return team;
	}

	@Override
	public Image getImage() {
		// return the unit's image
		return img;
	}

	@Override
	public void update(Summoner player) {
		// update movement
		
		if(hp <= 0){
			death();
		}
		
		attackDelayCounter--;

		
		Summon closest = null;
		int closest_distance = Integer.MAX_VALUE;
		int dist;

		for(int j = 0; j < Map.summons.length; j++){
			for(int i = 0; i < Map.summons[j].size(); i++){
				if(j != team){
					if((dist = Helper.dist(this, Map.summons[j].get(i))) < range && (closest == null || dist < closest_distance)){
						closest = Map.summons[j].get(i);
					}
				}
			}
		}

		attack(closest);

		/* placeholder
			if(x <= 0){
				goRight = true;
				stop = false;
			}
			else if(x >= Map.rightBound - summonSize){
				goRight = false;
				stop = false;
			}
		 */

		y = Map.lowerBound;
		
		
		if(ai == ATTACK){
			if(closest_distance > range){
				//if(attackDelayCounter <= 0){
					if(goRight){
						x += speed;
					}
					else{
						x -= speed;
					}
				//}
			}
		}
		else if(ai == FOLLOW && closest_distance > range){
			if(x < player.getX()){
				goRight = true;
				stop = false;
			}
			else if(x > player.getX()){
				goRight = false;
				stop = false;
			}
			else{
				stop = true;
			}
		}
		else{
			stop = true;
		}
		
		if(!stop){
			if(goRight){
				x += speed; 
			}
			else{
				x -= speed;
			}
		}
		if(hp == 0){
			dead = true;
		}
		
	
	}
	
	/*
<<<<<<< HEAD:src/src/Monster_1.java
	
=======*/
	private void attack(Summon target){
		if(target != null){
			target.damaged(attackDamage);
			attackDelayCounter = attackDelay;
		}
	}
	
	@Override
	public void damaged(int d){
		hp -= d;
	}
	
	//>>>>>>> Semester 2 update:src/Monster_1.java
	
	@Override
	public void update() {
		// update movement
		
		if(x <= 0){
			goRight = true;
		}
		else if(x >= Map.rightBound - summonSize){
			goRight = false;
		}
			
		y = Map.lowerBound;
		
		
		if(ai != GUARD){
			if(goRight){
				x += speed; 
			}
			else{
				x -= speed;
			}
		}
		
		if(hp == 0){
			dead = true;
		}
		hp--;
	}

	@Override
	public void death() {
		// TODO Handle unit death
		Map.summons[team].remove(this);
	}

	@Override
	public void setTeam(int newTeam) {
		team = newTeam;
	}

	@Override
	public void setX(int newX) {
		x = newX;
	}

	@Override
	public void setY(int newY) {
		y = newY;
	}

	@Override
	public int getManaCost() {
		return manaCost;
	}

	@Override
	public int getAI() {
		return ai;
	}

	@Override
	public void changeAI() {
		ai = (ai + 1) % 3;
		
	}
	
	public boolean isDead(){
		return dead;
	}

}
