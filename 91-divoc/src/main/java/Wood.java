public class Wood{
	private static int wood, burnedWood, overallWood, multiplier;
	
	public Wood(int wood, int overallWood, int burnedWood) {
		this.wood = wood;
		this.overallWood = overallWood;
		this.burnedWood = burnedWood;
		this.multiplier = 1;
	}
	
	public static int getWood() {
		return wood;
	}
	
	public static void addWood(int add) {
		wood += add*multiplier;
		overallWood += add*multiplier;
	}
	
	public static void removeWood(int remove) {
		wood -= remove;
	}
	
	public static int getMultiplier() {
		return multiplier;
	}
	
	public static void addMultiplier(int add) {
		multiplier += add;
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
