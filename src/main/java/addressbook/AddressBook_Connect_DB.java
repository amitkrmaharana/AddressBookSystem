package addressbook;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressBook_Connect_DB {

    String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSl=false";
    String userNane = "root";
    String password = "robowars@1amit";

    protected Connection getConnection(String url, String userNane, String password){
        Connection connection;
        try {
            connection = DriverManager.getConnection(url,userNane,password);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean isDBConnected(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed())
            return true;
        return false;
    }

    public List<Contacts> readData() {
        String sql = "select * from addressbook;";
        return getContactList(sql);
    }

    public int updateContactDeatils(String detailName, String detail, String firstName) {
        String sql = String.format("update addressbook set %s = '%s' where first_name = '%s';",detailName,detail,firstName);
        try(Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public List<Contacts> getAddressBookdata(ResultSet resultSet) {
        List<Contacts> contactsList = new ArrayList<>();
        try {
            while (resultSet.next()) {
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String address = resultSet.getString("address");
                String city = resultSet.getString("city");
                String state = resultSet.getString("state");
                String zip = resultSet.getString("zip");
                String phone = resultSet.getString("phone_number");
                String email = resultSet.getString("email");
                contactsList.add(new Contacts(firstName, lastName, address, city, state, zip, phone, email));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactsList;
    }

    public List<Contacts> reaadAndCountDateRangeDB(String start_date, String end_date) {
        String sql = String.format("select * from addressbook where start_date between '%s' and '%s';",Date.valueOf(start_date),Date.valueOf(end_date));
        return getContactList(sql);
    }

    public List<Contacts> getContactsByCity(String cityName) {
        String sql = String.format("select * from addressbook where city = '%s';",cityName);
        return getContactList(sql);
    }
    public List<Contacts> getContactList(String sql) {
        List<Contacts> contactList = new ArrayList<>();
        try(Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.getAddressBookdata(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    public List<Contacts> addNewContact(String first_name, String last_name, String type, String address, String city, String state, String zip, String phone_number, String email, String start_date) {
        String sql = String.format("insert into addressbook (first_name,last_name,type,address,city,state,zip,phone_number,email,start_date)" +
                " values ('%s','%s','%s','%s','%s','%s','%s','%s','%s','%s')",first_name,last_name,type,address,city,state,zip,phone_number,email,Date.valueOf(start_date));
        try(Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return readData();
    }

    public void addNewContactWithThreads(List<Contacts> contactList) {
        Map<Integer,Boolean> contactDetailsAdditionStatus  = new HashMap<>();
        for (Contacts contacts : contactList) {
            Runnable task = () -> {
              contactDetailsAdditionStatus.put(contacts.hashCode(),false);
              System.out.println("Contact being added: " + Thread.currentThread().getName());
              List<Contacts> contactsList = addNewContact(contacts.firstName, contacts.lastName, contacts.type, contacts.address, contacts.city, contacts.state, contacts.zip, contacts.phoneNumber, contacts.email, contacts.start_date);
              contactDetailsAdditionStatus.put(contacts.hashCode(),true);
              System.out.println("Contact Added: " + Thread.currentThread().getName());
            };
            //Threads implemented here
            Thread thread = new Thread(task,contacts.getFirstName());
            thread.start();
        }
        while (contactDetailsAdditionStatus.containsValue(false)) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

