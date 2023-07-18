import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmailApp {

	private static ArrayList<HasBirthday> birthdayList = new ArrayList<HasBirthday>();

	public void addNewRecipient(String details) {
		String[] s = details.split(":");
		String[] detailsList = s[1].trim().split(",");
		if (s[0].equals("Official")) {
			OfficialRecipient newOfficial = new OfficialRecipient(detailsList[0], detailsList[1], detailsList[2]);
		} 
		else if (s[0].equals("Office_friend")) {
			OfficeFriend newOfficeFriend = new OfficeFriend(detailsList[0], detailsList[1], detailsList[2],detailsList[3]);
			birthdayList.add(newOfficeFriend);
		} 
		else if (s[0].equals("Personal")) {
			PersonalRecipient newPersonal = new PersonalRecipient(detailsList[0], detailsList[1], detailsList[2],detailsList[3]);
			birthdayList.add(newPersonal);
		}

	}

	public void recipientWithBirthday(String birthday) {
		for (HasBirthday x : birthdayList) {
			if (birthday.equals(x.getBirthday())) {
				System.out.println(x.getName());
			}
		}
	}

	public void storeDetails(String details) {
		try {
			FileWriter writer = new FileWriter("clientList.txt", true);
			writer.append(details + "\r\n");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void createObjects() throws IOException {
		try {
			File file = new File("clientList.txt");
			file.createNewFile();
			Scanner reader = new Scanner(file);
			while (reader.hasNextLine()) {
				addNewRecipient(reader.nextLine());
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void sendBirthdayGreeting() throws IOException {
		LocalDate date = LocalDate.now();
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String string1 = date.format(formatter1);
		for (HasBirthday y : birthdayList) {
			if (y.getBirthday().substring(5).equals(string1.substring(5))) {

				SendEmail.sendMail(y.getEmail(), "Birthday Wish", y.getGreeting());
				Email email = new Email(y.getEmail(), "Birthday Wish", y.getGreeting());
				email.saveToHDD(string1.replace("/", "-"));
			}
		}
	}
}