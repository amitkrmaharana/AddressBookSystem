import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AddressBookMain {
    static Scanner input = new Scanner(System.in);
    static String[] info;
    static String name, addressBookName;
    static ArrayList<String> namelist = new ArrayList<>();
    static HashMap<String, HashMap> addressbooks = new HashMap<>();
    static HashMap<String, String[]> contacts = new HashMap<>();

    public static String[] Person() {
        String[] contact = new String[8];
        System.out.println("Enter your details accordingly \n1. First Name\n2. Last Name\n"
                + "3. House number\n4. City\n5. State\n6. Pin Code\n" +
                    "7.  Phone number\n8. e-mail");
        for (int index = 0; index < contact.length; index++)
            contact[index] = input.next();
        contacts.put(name, contact);
        addressbooks.put(addressBookName, contacts);
        return contact;
    }

    public static String[] edit(String[] contact) {
        System.out.println("Press the respective number you want to edit\n" +
                "1  First Name\n2 Last Name\n3 House no.\n4 City\n5 State\n" +
                "6 Pin Code\n7 phone number\n8 email");
        int choose = input.nextInt();
        if (choose >= 1 && choose <= 8) {
            choose--;
            System.out.println("Enter the new details you choosed to edit");
            contact[choose] = input.next();
        }
        return contact;
    }
    public static void option() {
        boolean bool = true;
        int action;
        while(bool) {
            System.out.println("Enter valid name of the address book where you stored the contact");
            name = input.next();
            if (namelist.contains(name)) {
                contacts = addressbooks.get(name);
                while (bool) {
                    System.out.println("Type the following numbers if you want to perform the corresponding action");
                    System.out.println("1. EDIT\n2. DELETE\n3. NEW CONTACT\nAnyNumber. EXIT");
                    action = input.nextInt();
                    switch (action) {
                        case 1:
                            System.out.println("Enter your unique name");
                            name = input.next();
                            if (namelist.contains(name)) {
                                info = contacts.get(name);
                                info = edit(info);
                            }
                            for (int index = 0; index < info.length; index++)
                                System.out.println(info[index]);
                            break;
                        case 2:
                            System.out.println("Enter your unique name");
                            name = input.next();
                            if (namelist.contains(name)) {
                                contacts.remove(name);
                                namelist.remove(name);
                            }
                            break;
                        case 3:
                            uniqueName();
                            info = Person();
                            for (int index = 0; index < info.length; index++)
                                System.out.println(info[index]);
                            break;
                        default:
                            bool = false;
                    }
                }
                bool = true;
            }
            else {
                System.out.println("Press the following: \n1. New Addressbook\n2. Try Again\nAny Number. Exit ");
                action = input.nextInt();
                switch(action) {
                    case 1:
                        start();
                        break;
                    case 2:
                        break;
                    default:
                        bool = false;
                }
            }
        }
    }
    public static String uniqueName() {
        boolean check = true;
        while(check == true) {
            name = input.next();
            if (namelist.contains(name))
                System.out.println("Name already exist.Please try again with another name");
            else {
                namelist.add(name);
                check = false;
            }
        }
        return name;
    }
    public static void start() {
        System.out.println("Enter a name for Address Book");
        addressBookName = uniqueName();
        System.out.println("Enter a name for contacts");
        name = uniqueName();
        info = Person();
    }
    public static void main(String[] args) {
        System.out.println("WELCOME to Address Book Program");
        start();
        option();
        System.out.println("******THANK YOU******");
    }
}