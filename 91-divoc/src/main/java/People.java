public class People{
	private static int people = 1;
	
	public People() {
	}
	
	public static int getPeople() {
		return people;
	}
	
	public static void addPeople(int add) {
		people += add;
	}
	
	public static void removePeople(int add) {
		people -= add;
	}
}
