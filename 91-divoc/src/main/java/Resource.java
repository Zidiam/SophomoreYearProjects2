import java.util.ArrayList;
import java.util.Random;

public class Resource{
	private static ArrayList<Resource> allResources = new ArrayList<Resource>();
	
	private int amount, overall, multiplier, odds;
	private String name;
	
	public Resource(String name, int amount, int overall) {
		this.name = name;
		this.amount = amount;
		this.overall = overall;
		this.multiplier = 1;
		odds = 100;
		allResources.add(this);
	}
	
	public int get() {
		return amount;
	}
	
	public void add(int add) {
		Random rand = new Random();
		if(rand.nextInt(100) <= odds) {
			amount += add*multiplier;
			overall += add*multiplier;	
		}
	}
	
	public void remove(int remove) {
		amount -= remove;
	}
	
	public int getMultiplier() {
		return multiplier;
	}
	
	public void addMultiplier(int add) {
		multiplier += add;
	}
	
	public int getOverall() {
		return overall;
	}
}
