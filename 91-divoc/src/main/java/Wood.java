public class Wood{
	private static int wood = 100;
	private static int overallWood = 100;
	
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
}
