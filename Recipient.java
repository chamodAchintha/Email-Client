package email_client;

public class Recipient {
	private String name;
	private String email;
	private static int count;

	public Recipient(String name, String email) {
		count++;
		this.name = name;
		this.email = email;
	}
	public static int getCount() {
		return count;
	}
	public String getName() {
		return this.name;
	}
	public String getEmail() {
		return this.email;
	}
}