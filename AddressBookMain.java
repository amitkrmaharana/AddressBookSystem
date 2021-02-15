import java.util.Scanner;

public class AddressBookMain {

	public String[] Person() {
		Scanner input = new Scanner(System.in);
		String[] contact = new String[8];
		System.out.println("Enter your First Name");
		String first_name = input.next();
		contact[0] = first_name;
		System.out.println("Enter your Last Name");
		String last_name = input.next();
		contact[1] = last_name;
		System.out.println("Enter your House number");
		String house_no = input.next();
		contact[2] = house_no;
		System.out.println("Enter your City name");
		String city = input.next();
		contact[3] = city;
		System.out.println("Enter your State name");
		String state = input.next();
		contact[4] = state;
		System.out.println("Enter your Pin Code");
		String pin_code = input.next();
		contact[5] = pin_code;
		System.out.println("Enter your Phone number");
		String phone_number = input.next();
		contact[6] = phone_number;
		System.out.println("Enter your e-mail address");
		String email = input.next();
		contact[7] = email;
		return contact;
		
	}
 
	public void edit(String[] contact) {
		System.out.println("If you want to edit then press 1 else press 0");//yso sysout  ctr+Space
		Scanner sc = new Scanner(System.in);
		int read = sc.nextInt();
		if (read == 1) {
			System.out.println("Press the respective number you want to edit\n1  First Name\n2 Last Name\n3 House no.\n4 City\n5 State\n6 Pin Code\n7 phone number\n8 email");
			int input = sc.nextInt();
			switch (input) {
			case 1:
				System.out.println("Enter your new First Name");
				String newFirstName = sc.next();
				contact[0] = newFirstName;
				break;
			case 2:
				System.out.println("Enter your new Last Name");
				String newLastName = sc.next();
				contact[1] = newLastName;
				break;
			case 3:
				System.out.println("Enter your new House Number");
				String newHouseNumber = sc.next();
				contact[2] = newHouseNumber;
				break;
			case 4:
				System.out.println("Enter your new City");
				String newCity = sc.next();
				contact[3] = newCity;
				break;
			case 5:
				System.out.println("Enter your new State");
				String newState = sc.next();
				contact[4] = newState;
				break;
			case 6:
				System.out.println("Enter your new Pin Code");
				String newPinCode = sc.next();
				contact[5] = newPinCode;
				break;
			case 7:
				System.out.println("Enter your new Phone number");
				String newPhoneNumber = sc.next();
				contact[6] = newPhoneNumber;
				break;
			case 8:
				System.out.println("Enter your new email");
				String newEmail = sc.next();
				contact[7] = newEmail;
				break;
			default:
				System.out.println("Invalid Input");
				break;
			}
			System.out.println("Your new details are as follows");
			for (int j = 0; j < contact.length; j++) {
				System.out.println(contact[j]);
			}
		} 
		else if (read == 0) {
			for (int j = 0; j < contact.length; j++) {
				System.out.println(contact[j]);
			}
			
		}
		else
			System.out.println("Invalid Input");
	}
	public void delete(String[] contact) {
		System.out.println("Enter the first name of the contact you want to delete");
		Scanner sc = new Scanner(System.in);
		String person_name = sc.next();
		if (contact[0].equals(person_name)) {
			for (int j = 0; j < contact.length; j++) {
				contact[j] = " ";
				System.out.println(contact[j]);
			}
			System.out.println("Your contact has been deleted");
		} 
		else {
			System.out.println("Contact name not available");
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("WELCOME to Address Book Program");
		AddressBookMain contact = new AddressBookMain();
		String[] info = contact.Person();
		System.out.println("Type the following numbers if you want to perform the corresponding action");
		System.out.println("1. EDIT\n2. DELETE\n3. NONE");
		int action = sc.nextInt();
		switch(action) {
		case 1:
			contact.edit(info);
			break;
		case 2:
			contact.delete(info);
			break;
		}
		System.out.println("******THANK YOU******");
	}

}

