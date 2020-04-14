public class Rabbit{
	private static int rabbit, overallRabbit, multiplier;
	private static boolean active;
	
	public Rabbit(int rabbit, int overall) {
		this.rabbit = rabbit;
		this.overallRabbit = overall;
		this.active = false;
		this.multiplier = 1;
	}
	
	public static int getMultiplier() {
		return multiplier;
	}
	
	public static void addMultiplier(int add) {
		multiplier += add;
	}
	
	public static int getRabbit() {
		return rabbit;
	}
	
	public static void addRabbit(int add) {
		rabbit += add * multiplier;
		overallRabbit += add * multiplier;
	}
	
	public static void removeRabbit(int remove) {
		rabbit -= remove;
	}
	
	public static void setActive(boolean v) {
		active = v;
	}
	
	public static boolean isActive() {
		return active;
	}
	
	public static int getOverall() {
		return overallRabbit;
	}
}
