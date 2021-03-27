package addressbook;

import org.apache.commons.collections.functors.FalsePredicate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook_Connect_DB {

    String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSl=false";
    String userNane = "root";
    String password = "robowars@1amit";

    protected Connection getConnection(String url, String userNane, String password) throws SQLException {
        Connection connection;
        connection = DriverManager.getConnection(url,userNane,password);
        return connection;
    }

    public boolean isDBConnected(Connection connection) throws SQLException {
        if (connection != null && !connection.isClosed())
            return true;
        return false;
    }

    public List<Contacts> readData() throws SQLException {
        String sql = "select * from addressbook;";
        List<Contacts> contactArrayList = new ArrayList<>();
        try (Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactArrayList = getAddressBookdata(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactArrayList;
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
        List<Contacts> contactList = new ArrayList<>();
        String sql = String.format("select * from addressbook where start_date between '%s' and '%s';",Date.valueOf(start_date),Date.valueOf(end_date));
        try(Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList= this.getAddressBookdata(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }

    public List<Contacts> getContactsByCity(String cityName) {
        List<Contacts> contactList = new ArrayList<>();
        String sql = String.format("select * from addressbook where city = '%s';",cityName);
        try(Connection connection = this.getConnection(jdbcURL,userNane,password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            contactList = this.getAddressBookdata(resultSet);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contactList;
    }
}

