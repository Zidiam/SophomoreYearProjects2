import java.util.ArrayList;

public class Story {
	private static ArrayList<String> stories = new ArrayList<String>();
	
	public Story() {
		stories = new ArrayList<String>();
	}
	
	public static void addStory(String story) {
		stories.add(story);
	}
	
	public static void removeStory(String remove) {
		stories.remove(stories.indexOf(remove));
	}
	
	public static ArrayList<String> getStory() {
		return stories;
	}
	
}
