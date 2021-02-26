package com.addressbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBookMain {
    static String firstName,lastName,address,city,state,zip,phoneNumber,email,name;
    static int selection, count = 0;
    static HashMap<String, HashMap> addressBook = new HashMap<>();
    static HashMap<String, String> contactDetails;
    static HashMap<String, HashMap> contactBook = new HashMap<>();
    static HashMap<HashMap, String> cityMap = new HashMap<>();
    static HashMap<HashMap, String> stateMap = new HashMap<>();
    static HashMap<HashMap, String> zipMap = new HashMap<>();
    static ArrayList<String> contactnameCheckList = new ArrayList<>();
    static ArrayList<String> addressBookNameCheckList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void detailInput() {
        contactDetails = new HashMap<>();
        System.out.println("Enter your First Name which is to be unique");
        firstName = uniqueNameCheck(contactnameCheckList);
        contactnameCheckList.add(firstName);
        contactDetails.put("First Name", firstName);
        System.out.println("Enter your Last Name");
        lastName = sc.next();
        contactDetails.put("Last Name", lastName);
        System.out.println("Enter your Address");
        address = sc.next();
        contactDetails.put("Address", address);
        System.out.println("Enter your City");
        city = sc.next();
        contactDetails.put("City", city);
        System.out.println("Enter your State");
        state = sc.next();
        contactDetails.put("State", state);
        System.out.println("Enter your Zip");
        zip = sc.next();
        contactDetails.put("Zip", zip);
        System.out.println("Enter your Phone Number");
        phoneNumber = sc.next();
        contactDetails.put("Phone Number", phoneNumber);
        System.out.println("Enter your Email");
        email = sc.next();
        contactDetails.put("Email", email);
        cityMap.put(contactDetails, city);
        stateMap.put(contactDetails, state);
        zipMap.put(contactDetails,zip);
        contactBook.put(firstName, contactDetails);
        count++;
        System.out.println("Details you enterd are " + contactDetails);
    }
    public static void editInfo() {
        System.out.println("Enter your First Name");
        firstName = sc.next();
        if (contactnameCheckList.contains(firstName)) {
            contactDetails = contactBook.get(firstName);
            contactBook.remove(firstName);
            System.out.println("Type a number corresponding to the detail edit it");
            System.out.println("1. First NAme\n2. Last Name\n3. Address\n" +
                    "4. City\n5. State\n6. Zip\n7. Phone Number\n8. Email");
            selection = sc.nextInt();
            switch(selection) {
                case 1:
                    contactnameCheckList.remove(firstName);
                    System.out.println("Enter your First Name");
                    contactDetails.remove("First Name");
                    firstName = uniqueNameCheck(contactnameCheckList);
                    contactDetails.put("First Name", firstName);
                    contactnameCheckList.add(firstName);
                    break;
                case 2:
                    System.out.println("Enter your Last Name");
                    contactDetails.remove("last Name");
                    contactDetails.put("Last Name", sc.next());
                    break;
                case 3:
                    System.out.println("Enter your Address");
                    contactDetails.remove("Address");
                    contactDetails.put("Address", sc.next());
                    break;
                case 4:
                    System.out.println("Enter your City");
                    contactDetails.remove("City");
                    contactDetails.put("City", sc.next());
                    break;
                case 5:
                    System.out.println("Enter your State");
                    contactDetails.remove("State");
                    contactDetails.put("State", sc.next());
                    break;
                case 6:
                    System.out.println("Enter your Zip");
                    contactDetails.remove("Zip");
                    contactDetails.put("Zip", sc.next());
                    break;
                case 7:
                    System.out.println("Enter your Phone Number");
                    contactDetails.remove("Phone Number");
                    contactDetails.put("Phone Number", sc.next());
                    break;
                case 8:
                    System.out.println("Enter your Email");
                    contactDetails.remove("Email");
                    contactDetails.put("Email", sc.next());
                    break;
                default:
                    System.out.println("Invalid Input");
            }
            contactBook.put(firstName,contactDetails);
        }
        else
            System.out.println("Invalid First Name");
    }
    public static void deleteInfo(HashMap<String,HashMap> book) {
        System.out.println("Enter your First Name");
        firstName = sc.next();
        book.entrySet().removeIf(entry -> entry.getKey().equals(firstName));
    }
    public static void checkAction() {
        boolean check = true;
        while(check) {
            System.out.println("Enter your Address Book unique name or to add new Address Book press any key to enter for next actions");
            name = sc.next();
            if (addressBookNameCheckList.contains(name)) {
                while (check) {
                    System.out.println("Type the number with respect to your choice:\n1. ADD CONTACT\n" +
                            "2 . EDIT CONTACT\n3. DELETE CONTACT\n4. SORT CONTACTBOOK by \n5. EXIT");
                    selection = sc.nextInt();
                    switch (selection) {
                        case 1:
                            detailInput();
                            addressBook.put(name, contactBook);
                            break;
                        case 2:
                            contactBook = addressBook.get(name);
                            editInfo();
                            break;
                        case 3:
                            contactBook = addressBook.get(name);
                            deleteInfo(contactBook);
                            break;
                        case 4:
                            contactBook = addressBook.get(name);
                            contactBook.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
                            break;
                        case 5:
                            check = false;
                            break;
                        default:
                            System.out.println("Invalid Input. Try Again");
                    }
                }
                check = true;
            }
            else {
                System.out.println("Enter the respective numbers for actions provided:\n1. NEW ADDRESS BOOK\n2. SORT BY CITY OR STATE" +
                        "\n3. TOTAL CONTACTS\n4. EXIT ");
                selection = sc.nextInt();
                switch(selection) {
                    case 1:
                        addressName();
                        break;
                    case 2:
                        sortingByCityOrStateorZip();
                        break;
                    case 3:
                        System.out.println("Total contacts available are " + cityMap.size());
                        break;
                    case 4:
                        check = false;
                        break;
                    default:
                        System.out.println("Invalid Input.Try Again");
                }
            }
        }
    }
    public static void addressName() {
        System.out.println("Enter a unique name for your Address Book");
        name = uniqueNameCheck(addressBookNameCheckList);
        addressBookNameCheckList.add(name);
    }
    public static void sortingByCityOrStateorZip() {
        System.out.println("Enter the respective number for actio provided: \n1. Sort by City\n2. Sort by State\n3. Sort by Zip");
        selection = sc.nextInt();
        switch(selection) {
            case 1:
                cityMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
                break;
            case 2:
                stateMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
                break;
            case 3:
                zipMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
                break;
            default:
                System.out.println("Invalid Input");
        }
    }
    public static String uniqueNameCheck(ArrayList<String> checkList) {
        boolean check = true;
        while(check) {
            name = sc.next();
            if (checkList.contains(name))
                System.out.println("Name already exist.Please try another name");
            else {
                check = false;
            }
        }
        return name;
    }
    public static void main(String[] args) {
        System.out.println("Welcome to the Address Book");
        addressName();
        checkAction();
        System.out.println("********Thank You********");
    }
}
