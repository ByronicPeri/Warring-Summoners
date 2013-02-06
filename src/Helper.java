//package src;
//import Summon;


public class Helper {
	public static int dist(Summon s1, Summon s2){
		return (int) Math.sqrt((s1.getX() - s2.getX())*(s1.getX() - s2.getX()) + (s1.getY() - s2.getY())*(s1.getY() - s2.getY()));
	}
}
