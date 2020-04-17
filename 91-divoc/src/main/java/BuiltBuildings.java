import java.util.ArrayList;

public class BuiltBuildings {
	private static ArrayList<Building> buildings = new ArrayList<Building>();
	private static int amount = 0;
	
	public BuiltBuildings() {
	}
	
	public static ArrayList<Building> get() {
		return buildings;
	}
	
	public static void add(Building build) {
		buildings.add(build);
	}
	
	public static void removeall() {
		buildings.clear();
		amount = 0;
	}
	
	public static void addAmount(int a) {
		amount ++;
	}
	
	public static int getAmount() {
		int temp = 0;
		for(int x = 0; x < buildings.size(); x++) {
			if(buildings.get(x).isBuilt()) {
				temp ++;
			}
		}
		return temp + amount;
	}
	
}
