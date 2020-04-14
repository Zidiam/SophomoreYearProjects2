public class Wheat{
	private static int wheat, overallWheat;
	private static boolean active;
	
	public Wheat(int wheat, int overall) {
		this.wheat = wheat;
		this.overallWheat = overall;
		this.active = false;
	}
	
	public static int getWheat() {
		return wheat;
	}
	
	public static void addWheat(int add) {
		wheat += add;
		overallWheat += add;
	}
	
	public static void removeWheat(int remove) {
		wheat -= remove;
	}
	
	public static void setActive(boolean v) {
		active = v;
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static int getOverall() {
		return overallWheat;
	}
}
