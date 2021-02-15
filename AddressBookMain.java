import java.util.Scanner;

public class AddressBookMain {

	public String[][] Person(String[][] contact) {
		boolean bool = false;
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Enter a unique name as your contact name");
			String name = input.next();
			for (int i = 0; i < contact[0].length; i++) {
				if (contact[i][0].equals(name)) {
					bool = false;
					break;
				} else {
					bool = true;
				}
			}
			int len = contact[0].length;
			if (bool == true) {
				contact[len][0] = name;
				System.out.println("Enter your First Name");
				String first_name = input.next();
				contact[len][1] = first_name;
				System.out.println("Enter your Last Name");
				String last_name = input.next();
				contact[len][2] = last_name;
				System.out.println("Enter your House number");
				String house_no = input.next();
				contact[len][3] = house_no;
				System.out.println("Enter your City name");
				String city = input.next();
				contact[len][4] = city;
				System.out.println("Enter your State name");
				String state = input.next();
				contact[len][5] = state;
				System.out.println("Enter your Pin Code");
				String pin_code = input.next();
				contact[len][6] = pin_code;
				System.out.println("Enter your Phone number");
				String phone_number = input.next();
				contact[len][7] = phone_number;
				System.out.println("Enter your e-mail address");
				String email = input.next();
				contact[len][8] = email;
			}
			else
				System.out.println("Contact name not available");
		}
		return contact;
		
	}
 
	public String[][] edit(String[][] contact, int len) {
			try (Scanner sc = new Scanner(System.in)) {
				System.out.println("Press the respective number you want to edit\n1  First Name\n2 Last Name\n3 House no.\n4 City\n5 State\n6 Pin Code\n7 phone number\n8 email");
				int input = sc.nextInt();
				switch (input) {
				case 1:
					System.out.println("Enter your new First Name");
					String newFirstName = sc.next();
					contact[len][1] = newFirstName;
					break;
				case 2:
					System.out.println("Enter your new Last Name");
					String newLastName = sc.next();
					contact[len][2] = newLastName;
					break;
				case 3:
					System.out.println("Enter your new House Number");
					String newHouseNumber = sc.next();
					contact[len][3] = newHouseNumber;
					break;
				case 4:
					System.out.println("Enter your new City");
					String newCity = sc.next();
					contact[len][4] = newCity;
					break;
				case 5:
					System.out.println("Enter your new State");
					String newState = sc.next();
					contact[len][5] = newState;
					break;
				case 6:
					System.out.println("Enter your new Pin Code");
					String newPinCode = sc.next();
					contact[len][6] = newPinCode;
					break;
				case 7:
					System.out.println("Enter your new Phone number");
					String newPhoneNumber = sc.next();
					contact[len][7] = newPhoneNumber;
					break;
				case 8:
					System.out.println("Enter your new email");
					String newEmail = sc.next();
					contact[len][8] = newEmail;
					break;
				default:
					System.out.println("Invalid Input");
					break;
				}
			}
			return contact;
	}
	public String[][] delete(String[][] contact, int len) {
		System.out.println("Enter the first name of the contact you want to delete");
		try (Scanner sc = new Scanner(System.in)) {
			String person_name = sc.next();
			if (contact[0][1].equals(person_name)) {
				for (int j = 0; j < contact[0].length; j++) {
					for (int i = 0; i < contact.length; i++) {
						contact[j][i] = " ";
					}		
				}
				System.out.println("Your contact has been deleted");
			} 
			else {
				System.out.println("Contact name not available");
			}
		}
		return contact;
	}
	
	public static void main(String[] args) {
		String name;
		String[][] contacts = new String[100][9];
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("WELCOME to Address Book Program");
			AddressBookMain contact = new AddressBookMain();
			String[][] info = contact.Person(contacts);
			boolean bool = true;
			while (bool == true) {
				System.out.println("Type the following numbers if you want to perform the corresponding action");
				System.out.println("1. EDIT\n2. DELETE\n3. ADD CONTACT");
				int action = sc.nextInt();
				switch(action) {
				case 1:
					System.out.println("Please type your contact name");
					name = sc.next();
					for (int i = 0; i < info[0].length; i++) {
						if (info[i][0].equals(name)) {
							info = contact.edit(info, i);
							break;
						}
						else
							System.out.println("Contact name  not available");
					}
					break;
				case 2:
					System.out.println("Please type your contact name");
					name  = sc.next();
					for (int i = 0; i < info[0].length; i++) {
						if (info[i][0].equals(name)) {
							info = contact.delete(info, i);
							break;
						}
						else
							System.out.println("Contact name  not available");
					}
					break;
				case 3:
					info = contact.Person(contacts);
					break;
				}
				System.out.println("Do you want to Continue Editing, if yes then press 1 else press 0");
				int yesno = sc.nextInt();
				if (yesno != 1) {
				}
				else
					bool = false;
			}
			for (int j = 0; j < info[0].length; j++) {
				for (int i = 0; i < info.length; i++) {
					System.out.println(info[j][i]);
				}	
			}
		}
		System.out.println("******THANK YOU******");
	}

}
