public abstract class HasBirthday extends Recipient {
	private String birthday;

	public HasBirthday(String name, String email, String birthday) {
		super(name, email);
		this.birthday = birthday;
	}

	public String getBirthday() {
		return this.birthday;
	}
	public String getEmail() {
		return super.getEmail();
	}
	public String getName() {
		return super.getName();
	}
	public abstract String getGreeting();
}