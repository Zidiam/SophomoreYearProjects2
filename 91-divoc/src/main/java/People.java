public class People{
	private static int people;
	
	public People(int people) {
		this.people = people;
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
