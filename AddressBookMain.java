import java.util.*;

public class AddressBookMain {

	public void Person() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your First Name");
		String first_name = input.next();
		System.out.println("Enter your Last Name");
		String last_name = input.next();
		System.out.println("Enter your House number");
		String house_no = input.next();
		System.out.println("Enter your City name");
		String city = input.next();
		System.out.println("Enter your State name");
		String state = input.next();
		System.out.println("Enter your Pin Code");
		String pin_code = input.next();
		System.out.println("Enter your Phone number");
		String phone_number = input.next();
		System.out.println("Enter your e-mail address");
		String email = input.next();
		System.out.println("First Name: " + first_name);
		System.out.println("Last Name: " + last_name);
		System.out.println("House Number: " + house_no);
		System.out.println("City: " + city);
		System.out.println("State: " + state);
		System.out.println("Pin Code: " + pin_code);
		System.out.println("Phone Number: " + phone_number);
		System.out.println("E-Mail: " + email);
	}
	
	public static void main(String[] args) {
		System.out.println("WELCOME to Address Book Program");
		AddressBookMain contact = new AddressBookMain();
		contact.Person();
		
	}
}
