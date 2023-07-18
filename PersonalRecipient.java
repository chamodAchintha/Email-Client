public class PersonalRecipient extends HasBirthday {
    private String nickName;
	private static String greeting = "Happy birthday to somebody so very dear to my heart!. Chamod";
    
	public PersonalRecipient(String name, String nickName, String email, String birthday) {
		super(name, email, birthday);
		this.nickName = nickName;
	}

	@Override
	public String getGreeting() {
		return greeting;
	}
}