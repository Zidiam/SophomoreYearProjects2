public class Stone{
	private static int stone, overallStone, multiplier;
	private static boolean active;
	
	public Stone(int stone, int overall) {
		this.stone = stone;
		this.overallStone = overall;
		this.active = false;
		this.multiplier = 1;
	}
	
	public static int getMultiplier() {
		return multiplier;
	}
	
	public static void addMultiplier(int add) {
		multiplier += add;
	}
	
	public static int getStone() {
		return stone;
	}
	
	public static void addStone(int add) {
		stone += add * multiplier;
		overallStone += add * multiplier;
	}
	
	public static void removeStone(int remove) {
		stone -= remove;
	}
	
	public static void setActive(boolean v) {
		active = v;
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static int getOverall() {
		return overallStone;
	}
}
