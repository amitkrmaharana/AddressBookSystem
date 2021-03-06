package addressbook;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class AddressBookMain {
    static HashMap<String,HashMap> addressBook = new HashMap<>();
    static ArrayList<String> addressBookNameList = new ArrayList<>();
    public Contacts contact;
    static Scanner consoleReader = new Scanner(System.in);
    static String firstName;
    ArrayList<Contacts> contactsArrayList = new ArrayList<>();
    static int totalContactsCount = 0;
    AddressBookFileIOService addressBookFileIOService = new AddressBookFileIOService();

    public void createContact(HashMap<String, Contacts> contactsBook, boolean check) {
        while(check) {
            firstName = getDetails("First Name");
            if (contactsBook.keySet().contains(firstName)) {System.out.println("Name Already Exist. Try Again!!");}
            else {check = false;}
        }
        String lastName = getDetails("Last Name");
        String address = getDetails("Address");
        String city = getDetails("City");
        String state = getDetails("State");
        String zip = getDetails("Zip");
        String phoneNumber = getDetails("Phone Number");
        String email = getDetails("Email");
        contact = new Contacts(firstName,lastName,address,city,state,zip,phoneNumber,email);
        contactsBook.put(firstName,contact);
        contactsArrayList.add(contact);
        totalContactsCount++;
    }

    public String getDetails(String input) {
        System.out.println("Enter " + input + ": ");
        return consoleReader.next();
    }

    public void updateContact(HashMap<String,Contacts> contactsBook) {
        String name = getDetails("First Name");
        if(contactsBook.containsKey(name)) { traverseContactDetails(contactsBook.get(name),contactsBook); }
        else { System.out.println("Name does not exist"); }
    }

    public void traverseContactDetails(Contacts contact,HashMap<String,Contacts> contactsBook) {
        System.out.println("Press the corresponding number you want to edit\n" +
                "1. First Name\n2. Last Name\n3. Address \n4. City\n5. State\n" +
                "6. Zip\n7. phone number\n8. email");
        switch (consoleReader.nextInt()) {
            case 1:
                String name = contact.getFirstName();
                contact.setFirstName(getDetails("First Name"));
                contactsBook.put(contact.getFirstName(),contact);
                contactsBook.remove(name);
                break;
            case 2:
                contact.setLastName(getDetails("Last Name"));
                break;
            case 3:
                contact.setAddress(getDetails("Address"));
                break;
            case 4:
                contact.setCity(getDetails("City"));
                break;
            case 5:
                contact.setState(getDetails("State"));
                break;
            case 6:
                contact.setZip(getDetails("Zip"));
                break;
            case 7:
                contact.setPhoneNumber(getDetails("Phone Number"));
                break;
            case 8:
                contact.setEmail(getDetails("Email"));
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    public void traverseAddressBook() {
        boolean check = true;
        String name;
        HashMap<String,Contacts> contactsBook;
        while (check) {
            System.out.println("Choose Options From the Following, Type Corresponding number" +
                    "\n1. Add New Address Book\n2. Existing Address Book\n3. Search Person Details By City or State\n4. Total Number of Contacts" +
                    "\n5. Sort Contacts\n6. Read FileIO data\n7. Write FileIO data\n8. Read CSV File\n9. Write CSV File\n10. Read Json File\n" +
                    "11. Write To Json File \n12. Exit");
            switch (consoleReader.nextInt()) {
                case 1:
                    name = getDetails("New Address Book Name");
                    if(addressBookNameList.contains(name))
                        System.out.println("Name Already Exist");
                    else {
                        contactsBook = new HashMap<>();
                        contactsBook = traverseContactBook(check, contactsBook);
                        addressBook.put(name, contactsBook);
                        addressBookNameList.add(name);
                    }
                    break;
                case 2:
                    name  = getDetails("Address Book Name");
                    if (addressBook.containsKey(name)) {
                        contactsBook = addressBook.get(name);
                        traverseContactBook(check,contactsBook);
                    }else { System.out.println("Invalid Name"); }
                    break;
                case 3:
                    searchContactByCityOrState();
                    break;
                case 4:
                    System.out.println("Total Number of Contacts are: " + totalContactsCount);
                    break;
                case 5:
                    sortContact();
                    break;
                case 6:
                    readAddressBook();
                    break;
                case 7:
                    writeAddressBook();
                    addressBookFileIOService.printData();
                    break;
                case 8:
                    readCSVFile();
                    break;
                case 9:
                    writeCSVFile();
                    break;
                case 10:
                    readFromJsonFile();
                    break;
                case 11:
                    writeToJsonFile();
                    break;
                case 12:
                    check = false;
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
    }

    public void searchContactByCityOrState() {
        String name = getDetails("City or State Name to Find Person Contacts in it");
        contactsArrayList.stream().filter(contact-> contact.getCity().equals(name) || contact.getState().equals(name)).map(person->person.toString()).forEach(System.out::println);
    }

    public HashMap traverseContactBook(boolean check,HashMap<String,Contacts> contactsBook) {
        while (check) {
            System.out.println("Choose Options from the following, Type the correponding number" +
                    "\n1. Add New Contact\n2. Update Contact\n3. Delete Contact\n4. Print Contact Book\n5. Exit");
            switch (consoleReader.nextInt()) {
                case 1:
                    createContact(contactsBook,check);
                    check = true;
                    break;
                case 2:
                    updateContact(contactsBook);
                    break;
                case 3:
                    deleteContact(contactsBook);
                    break;
                case 4:
                    toPrint(contactsBook);
                    break;
                case 5:
                    check = false;
                    break;
                default:
                    System.out.println("Invalid Input, Try Again!!");
            }
        }
        check = true;
        return contactsBook;
    }

    public void deleteContact(HashMap<String,Contacts> contactsBook) {
        System.out.println("Enter First Name of the Contact to be Deleted");
        String name = consoleReader.next();
        if (contactsBook.containsKey(name)) {
            contactsArrayList.remove(contactsArrayList.indexOf(contactsBook.get(name)));
            contactsBook.remove(name);
            totalContactsCount--;
        }
        else { System.out.println("Name does not exist"); }
    }

    public void sortContact() {
        System.out.println("Sort By::\n1. First Name\n2. City\n3. State\n4. Zip");
        switch (consoleReader.nextInt()) {
            case 1:
                contactsArrayList.stream().sorted(Comparator.comparing(Contacts::getFirstName)).forEach(System.out::println);
                break;
            case 2:
                contactsArrayList.stream().sorted(Comparator.comparing(Contacts::getCity)).forEach(System.out::println);
                break;
            case 3:
                contactsArrayList.stream().sorted(Comparator.comparing(Contacts::getState)).forEach(System.out::println);
                break;
            case 4:
                contactsArrayList.stream().sorted(Comparator.comparing(Contacts::getZip)).forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid Input");
        }
    }

    public void toPrint(HashMap<String,Contacts> contactsBook) {
        contactsBook.forEach((String,Contacts)-> System.out.println(String + " " + Contacts) );
    }

    public void writeAddressBook() {
        new AddressBookFileIOService().write(contactsArrayList);
    }

    public void readAddressBook() {
        contactsArrayList = (ArrayList<Contacts>) new AddressBookFileIOService().readData();
    }
    public void readCSVFile() {
        try {
            // Initiating the CSVreader class
            CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Milan\\Desktop\\Fellowship\\ContactBooks\\src\\main\\resources\\contactDetails.csv"));
            //Reading contents of CSV file
            StringBuffer buffer = new StringBuffer();
            String line[];
            while ((line = reader.readNext()) != null) {
                for (int index = 0; index< line.length; index++)
                    System.out.println(line[index] + "  ");
                System.out.println("  ");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCSVFile() {
        try (Writer writer = Files.newBufferedWriter(Paths.get("C:\\Users\\Milan\\Desktop\\Fellowship\\ContactBooks\\src\\main\\resources\\contactDetails.csv"));
        ){
            StatefulBeanToCsv<Contacts> beanToCsv = new StatefulBeanToCsvBuilder(writer)
                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
                    .build();
            List<Contacts> csvUsers = new ArrayList<>();
            for (Contacts contact : contactsArrayList) {
                csvUsers.add(new Contacts(contact.getFirstName(), contact.getLastName(), contact.getAddress(),
                        contact.getCity(), contact.getState(), contact.getZip(), contact.getPhoneNumber(), contact.getEmail()));
            }
            beanToCsv.write(csvUsers);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        } catch (CsvDataTypeMismatchException e) {
            e.printStackTrace();
        }
    }

    public void writeToJsonFile() {
        try {
            Gson gson = new Gson();
            String jsonObject = gson.toJson(contactsArrayList);
            FileWriter writer = new FileWriter("src/main/resources/contactBook.json");
            writer.write(jsonObject);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromJsonFile() {
        Type typeToken = new TypeToken<List<Contacts>>() {}.getType();
        List<Contacts> contactlist;
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/contactBook.json"));
            contactlist = gson.fromJson(reader,typeToken);
            contactlist.forEach(System.out::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AddressBookMain addressBookMain = new AddressBookMain();
        System.out.println("Welcome to Address Book Program");
        addressBookMain.traverseAddressBook();
        System.out.println("**********Thank You**********");
    }

}