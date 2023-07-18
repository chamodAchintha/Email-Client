public class OfficialRecipient extends Recipient {
	private String designation;

	public OfficialRecipient(String name, String email, String designation) {
		super(name, email);
		this.designation = designation;
	}
}