//package src;
import java.awt.Image;



public interface Summon {
	//int hp = 0;
	public void damaged(int d);
	
	public int getX();
	public int getY();
	public void setX(int newX);
	public void setY(int newY);
	public int getSpeed();
	public int getHP();
	public int getMaxHP();
	public int getAttackDamage();
	public int getAttackDelay();
	public int getTeam();
	public void setTeam(int newTeam);
	public Image getImage();
	public void update();
	public void update(Summoner player);
	public void death();
	public int getManaCost();
	public int getAI();
	public void changeAI();
	public boolean isDead();
}
