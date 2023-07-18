import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Email implements Serializable {
	private String recipient;
	private String subject;
	private String content;

	protected Email(String recipient, String subject, String content) {
		this.recipient = recipient;
		this.subject = subject;
		this.content = content;
	}
	
	//method to get information of sent emails
	public String getInfo() {
		return "Subject:" + this.subject + "    " + "Recipient:" + this.recipient;
	}
	
	// save Email objects to HDD using serialization
	public void saveToHDD(String currentDate) throws IOException {
		File pathAsFile = new File(currentDate);
		pathAsFile.mkdir();
		File file = new File(this.recipient + ".ser");
		FileOutputStream fos = new FileOutputStream(file);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(this);
	}

	public static void sentEmailDetails(String date) throws IOException, ClassNotFoundException {
		if (Files.exists(Paths.get(date))) {                   //check there is a folder in input date
			File directoryPath = new File(date);
			String emailList[] = directoryPath.list();     //list all files in input date folder

			for (String str : emailList) {
				File file = new File(date + "/" + str);
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				Email obj = (Email) ois.readObject();                 //for each email object in input date folder
				System.out.println(obj.getInfo());                    // print subject and recipient
			}
		}
	}
}