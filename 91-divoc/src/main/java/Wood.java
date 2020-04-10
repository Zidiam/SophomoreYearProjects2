public class Wood{
	private static int wood = 0;
	private static int overallWood = 0;
	private static int burnedWood = 0;
	
	public Wood() {
	}
	
	public static int getWood() {
		return wood;
	}
	
	public static void addWood(int add) {
		wood += add;
		overallWood += add;
	}
	
	public static void removeWood(int remove) {
		wood -= remove;
	}
	
	public static int getOverall() {
		return overallWood;
	}
	
	public static void addBurnedWood(int add) {
		burnedWood += add;
	}
	
	public static int getBurnedWood() {
		return burnedWood;
	}
}
