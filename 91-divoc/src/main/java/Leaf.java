import java.util.Random;

public class Leaf{
	private static int leaf, overallLeaf, odds;
	
	public Leaf(int leaf, int overall) {
		this.leaf = leaf;
		this.overallLeaf = overall;
		this.odds = 100;
	}
	
	public static int getLeaf() {
		return leaf;
	}
	
	public static void addLeaf(int add) {
		Random rand = new Random();
		if(rand.nextInt(odds) <= 50) {
			leaf += add;
			overallLeaf += add;
		}
	}
	
	public static void removeLeaf(int remove) {
		leaf -= remove;
	}
	
	public static int getOverall() {
		return overallLeaf;
	}
}
