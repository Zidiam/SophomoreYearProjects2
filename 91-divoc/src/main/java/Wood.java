public class Wood{
	private static int wood = 1;
	
	public Wood() {
	}
	
	public static int getWood() {
		return wood;
	}
	
	public static void addWood(int add) {
		wood += add;
	}
	
	public static void removeWood(int remove) {
		wood -= remove;
	}
}
