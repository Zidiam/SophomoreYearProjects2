import java.util.ArrayList;

public class Story {
	public static ArrayList<String> stories;
	
	public Story() {
		stories = new ArrayList<String>();
	}
	
	public static void addStory(String add) {
		stories.add(add);
	}
	
}
