public class OfficeFriend extends HasBirthday {
	private String designation;
	private static final String greeting = "Happy birthday! May the years ahead be filled with joy and happiness!. Chamod";

	public OfficeFriend(String name, String email, String designation, String birthday) {
		super(name, email, birthday);
		this.designation = designation;
	}

	@Override
	public String getGreeting() {
		return greeting;
	}
}
