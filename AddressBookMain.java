public class AddressBookMain {
	private String first_name;
	private String last_name;
	private int House_no;
	private String city;
	private String state;
	private int pin_code;
	private String phone_number;
	private String email;
	
	public AddressBookMain(String first_name, String last_name, int House_no, String city, String state, int pin_code, String phone_number, String email) {
		this.first_name = first_name;
		this.last_name = last_name;
		this.House_no = House_no;
		this.city = city;
		this.state = state;
		this.pin_code = pin_code;
		this.phone_number = phone_number;
		this.email = email;
	}
	
	public static void main(String[] args) {
		System.out.println("WELCOME to Address Book Program");
		AddressBookMain entry = new AddressBookMain("Amit", "Maharana", 77, "Jamshedpur", "Jharkhand", 831004, "8210029078", "ak657harley@gmail.com");
		System.out.println("First name: " + entry.first_name);
		System.out.println("Last name: " + entry.last_name);
		System.out.println("House number: " + entry.House_no);
		System.out.println("City: " + entry.city);
		System.out.println("State: " + entry.state);
		System.out.println("Pin code: " + entry.pin_code);
		System.out.println("Phone number: " + entry.phone_number);
		System.out.println("E-Mail: " + entry.email);
	}
}

