package addressbook;

import java.util.Date;
import java.util.Objects;

public class Contacts {
    protected String start_date;
    protected String type;
    protected String firstName;
    protected String lastName;
    protected String address;
    protected String city;
    protected String state;
    protected String zip;
    protected String phoneNumber;
    protected String email;
    public int id;

    public Contacts(String firstName, String lastName, String address, String city, String state, String zip, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contacts(String firstName, String lastName, String type, String address, String city, String state, String zip, String phoneNumber, String email, String start_date) {
        this(firstName, lastName, address, city, state, zip, phoneNumber, email);
        this.type = type;
        this.start_date = start_date;
    }

    public Contacts(int id, String firstName, String lastName, String type, String address, String city, String state, String zip, String phoneNumber, String email, String start_date) {
        this(firstName, lastName, type, address, city, state, zip, phoneNumber, email, start_date);
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contacts contacts = (Contacts) o;
        return Objects.equals(start_date, contacts.start_date) && Objects.equals(type, contacts.type) && Objects.equals(firstName, contacts.firstName) && Objects.equals(lastName, contacts.lastName) && Objects.equals(address, contacts.address) && Objects.equals(city, contacts.city) && Objects.equals(state, contacts.state) && Objects.equals(zip, contacts.zip) && Objects.equals(phoneNumber, contacts.phoneNumber) && Objects.equals(email, contacts.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, type, address, city, state, zip, phoneNumber, email, start_date);
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zip=" + zip +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getFirstName() {
        return firstName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getZip() {
        return zip;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
