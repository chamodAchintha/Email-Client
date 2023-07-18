import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class EmailClient {

	static {
		EmailApp emailApp = new EmailApp();
		// create objects for each recipient in clientList.txt
		try {
			emailApp.createObjects();
			// send birthday greetings
			emailApp.sendBirthdayGreeting();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		EmailApp emailApp = new EmailApp();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter option type: \n" 
				+ "1 - Adding a new recipient\n"
				+ "2 - Sending an email\n"
				+ "3 - Printing out all the recipients who have birthdays\n"
				+ "4 - Printing out details of all the emails sent\n"
				+ "5 - Printing out the number of recipient objects in the application");

		int option = scanner.nextInt();

		Scanner in = new Scanner(System.in);
		switch (option) {
		case 1:
			String details = in.nextLine();
			// calling the method to add a new recipient
			emailApp.addNewRecipient(details);
			// store details in clientList.txt file
			emailApp.storeDetails(details);
			break;

		case 2:
			String str = in.nextLine();
			String[] details1 = str.trim().split(",");
			// calling method to send an email
			SendEmail.sendMail(details1[0], details1[1], details1[2]);

			LocalDate date = LocalDate.now();
			DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
			String string1 = date.format(formatter1);
			// save sent email to hard disk
			Email email = new Email(details1[0], details1[1], details1[2]);
			email.saveToHDD(string1.replace("/", "-"));
			break;
		case 3:
			String birthdate = in.nextLine();
			// calling the method to print recipients who have birthdays on the given date
			emailApp.recipientWithBirthday(birthdate);
			break;
		case 4:
			String dateIn = in.nextLine();
			// calling method to print the details of all the emails sent on the input date
			Email.printEmailDetails(dateIn.replace("/", "-"));
			break;
		case 5:
			// calling the method to print the number of recipient objects in the
			// application
			System.out.println(Recipient.getCount());
			break;
		}
		scanner.close();
		in.close();
	}

}