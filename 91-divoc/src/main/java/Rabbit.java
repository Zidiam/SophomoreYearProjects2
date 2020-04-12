public class Rabbit{
	private static int rabbit, overallRabbit;
	
	public Rabbit(int rabbit, int overall) {
		this.rabbit = rabbit;
		this.overallRabbit = overall;
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
	
	public static int getOverall() {
		return overallRabbit;
	}
}
