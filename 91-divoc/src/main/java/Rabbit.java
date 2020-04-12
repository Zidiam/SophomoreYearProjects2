public class Rabbit{
	private static int rabbit, overallRabbit;
	private static boolean active;
	
	public Rabbit(int rabbit, int overall) {
		this.rabbit = rabbit;
		this.overallRabbit = overall;
		this.active = false;
	}
	
	public static int getRabbit() {
		return rabbit;
	}
	
	public static void addRabbit(int add) {
		rabbit += add;
		overallRabbit += add;
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
