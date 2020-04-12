public class Wood{
	private static int wood, burnedWood, overallWood;
	
	public Wood(int wood, int overallWood, int burnedWood) {
		this.wood = wood;
		this.overallWood = overallWood;
		this.burnedWood = burnedWood;
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
