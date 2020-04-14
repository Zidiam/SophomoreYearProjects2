import java.util.Random;

public class Leaf{
	private static int leaf, overallLeaf, odds, multiplier;
	
	public Leaf(int leaf, int overall) {
		this.leaf = leaf;
		this.overallLeaf = overall;
		this.odds = 100;
		this.multiplier = 1;
	}
	
	public static int getLeaf() {
		return leaf;
	}
	
	public static void addMultiplier(int add) {
		multiplier += add;
	}
	
	public static int getMultiplier() {
		return multiplier;
	}
	
	public static void addLeaf(int add) {
		Random rand = new Random();
		if(rand.nextInt(odds) <= 50) {
			leaf += add * multiplier;
			overallLeaf += add * multiplier;
		}
	}
	
	public static void removeLeaf(int remove) {
		leaf -= remove;
	}
	
	public static int getOverall() {
		return overallLeaf;
	}
}
