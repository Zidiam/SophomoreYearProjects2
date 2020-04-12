public class Leaf{
	private static int leaf, overallLeaf;
	
	public Leaf(int leaf, int overall) {
		this.leaf = leaf;
		this.overallLeaf = overall;
	}
	
	public static int getLeaf() {
		return leaf;
	}
	
	public static void addLeaf(int add) {
		leaf += add;
		overallLeaf += add;
	}
	
	public static void removeLeaf(int remove) {
		leaf -= remove;
	}
	
	public static int getOverall() {
		return overallLeaf;
	}
}
