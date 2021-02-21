import java.util.Scanner;

public class AddressBookMain {
	static Scanner input = new Scanner(System.in);
	static String[] info;

	public static String[] Person() {
		String[] contact = new String[8];
		System.out.println("Enter your details accordingly \n1. First Name\n2. Last Name\n"
				+ "3. House number\n4. City\n5. State\n6. Pin Code\n" + "7.  Phone number\n7. e-mail");
		for (int index = 0; index < contact.length; index++)
			contact[index] = input.next();
		return contact;
	}

	public static String[] edit(String[] contact) {
		System.out.println(
				"Press the respective number you want to edit\n1  First Name\n2 Last Name\n3 House no.\n4 City\n5 State\n6 Pin Code\n7 phone number\n8 email");
		int choose = input.nextInt();
		if (choose >= 1 && choose <= 8) {
			choose--;
			System.out.println("Enter the new details you choosed to edit");
			contact[choose] = input.next();
		}
		return contact;
	}

	public static String[] delete(String[] contact) {
		System.out.println("Enter the first name of the contact you want to delete");
		String person_name = input.next();
		if (contact[0].equals(person_name)) {
			for (int index = 0; index < contact.length; index++)
				contact[index] = null;
			System.out.println("Your contact has been deleted");
		} else {
			System.out.println("Contact name not available");
		}
		return contact;
	}

	public static void option() {
		boolean bool = true;
		while (bool) {
			System.out.println("Type the following numbers if you want to perform the corresponding action");
			System.out.println("1. EDIT\n2. DELETE\nAnyNumber. EXIT");
			int action = input.nextInt();
			switch (action) {
			case 1:
				info = edit(info);
				break;
			case 2:
				info = delete(info);
				break;
			default:
				bool = false;
			}
			for (int index = 0; index < info.length; index++) {
				System.out.println(info[index]);
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("WELCOME to Address Book Program");
		info = Person();
		option();
		System.out.println("******THANK YOU******");
	}
}