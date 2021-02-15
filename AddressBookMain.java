import java.util.Objects;
import java.util.Scanner;

public class AddressBookMain {
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String city;
    private final String state;
    private final int zip;
    private final String phone;
    private final String email;

    public AddressBookMain(String firstName, String lastName, String address, String city,
                                 String state, int zip, String phone, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    public String toString() {
        return "\tFirst Name: " + firstName + "\n\tLast Name: " + lastName + "\n\tAddress: " +
                address + "\n\tCity: " + city + "\n\tState: " + state + "\n\tZIP: " + zip +
                "\n\tPhone: " + phone + "\n\temail: " + email;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // Array Declaration
        AddressBookMain[] content = new AddressBookMain[1];
        //Instance variables
        int entries, zip;
        String first, last, address, city, state, phone, email, choice;

        //Asking User for 1 or more Entries
        System.out.println("How many new Entries do you require in AddressBook: ");
        entries = sc.nextInt();
        // Initialising Array
        if ( entries > 0)
            content = new AddressBookMain[entries];
        else {
            System.out.println("Must Create atleast 1 Entry");
            System.exit(0);
        }

        System.out.println("\t*****Address Book*****\t");
        for ( int i = 0 ; i < entries ; i++ ) {
            System.out.println("Entry: "+(i+1));
            System.out.print("First name? ");
            first = sc.next();
            System.out.print("Last name? ");
            last = sc.next();
            System.out.print("Address? ");
            address = sc.next();
            System.out.print("City? ");
            city = sc.next();
            System.out.print("State? ");
            state = sc.next();
            System.out.print("ZIP? ");
            zip = sc.nextInt();
            System.out.print("Phone? ");
            phone = sc.next();
            System.out.print("Email? ");
            email = sc.next();
            content[i] = new AddressBookMain(first, last, address, city, state, zip, phone, email);
        }

        for ( int i = 0 ; i < entries ; i++ ) {
            System.out.println("\t*****Address Book*****\t");
            System.out.println("Entry: "+(i+1));
            System.out.println(content[i]);
        }

        System.out.println("Do you want to edit any Entries u just created? (y/n)");
        choice = sc.next();
        if (choice.equals("Y") || choice.equals("y")) {
            String name;
            System.out.println("Please enter the first name of Person u want to edit: ");
            name = sc.next();
            for (int i = 0; i < entries; i++) {
                if (Objects.equals(content[i].firstName, name)) {
                    System.out.print("First name? ");
                    first = sc.next();
                    System.out.print("Last name? ");
                    last = sc.next();
                    System.out.print("Address? ");
                    address = sc.next();
                    System.out.print("City? ");
                    city = sc.next();
                    System.out.print("State? ");
                    state = sc.next();
                    System.out.print("ZIP? ");
                    zip = sc.nextInt();
                    System.out.print("Phone? ");
                    phone = sc.next();
                    System.out.print("Email? ");
                    email = sc.next();
                    content[i] = new AddressBookMain(first, last, address, city, state, zip, phone, email);
                } else
                    System.out.println("Person Not found at Entry " + (i + 1) + " with Person FirstName: " + name);
            }
            for (int i = 0; i < entries; i++) {
                System.out.println("\t*****Address Book*****\t");
                System.out.println("(Edited)Entry: " + (i + 1));
                System.out.println(content[i]);
            }
        }
        else
            System.out.println("Thank You!!");

        System.out.println("Do you want to delete any Entries u just created? (y/n)");
        choice = sc.next();
        if (choice.equals("Y") || choice.equals("y")) {
            AddressBookMain[] removedArray = new AddressBookMain[content.length-1];
            String name;
            System.out.println("Please enter the first name of Person u want to edit: ");
            name = sc.next();
            for (int i = 0, k=0; i < entries; i++) {
                if (Objects.equals(content[i].firstName, name)) {
                    continue;
                }
                removedArray[k++] = content[i];
            }
            entries = entries-1;
            System.arraycopy(removedArray, 0, content, 0, entries);
            for (int i = 0; i < entries; i++) {
                System.out.println("\t*****Address Book*****\t");
                System.out.println("Entry: " + (i + 1));
                System.out.println(content[i]);
            }
        }
        else
            System.out.println("******Thank You******");
    }
}
