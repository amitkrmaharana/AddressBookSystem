package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddressBookTest {
    private AddressBook_Connect_DB addressBook_connect_db = new AddressBook_Connect_DB();

    @Test
    public void givenCorrectJdbcUrlUsernamePassword_ShouldReturnTrue() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/address_book?useSSl=false";
        String userNane = "root";
        String password = "robowars@1amit";
        Connection connection = addressBook_connect_db.getConnection(jdbcURL,userNane,password);
        Assert.assertTrue(addressBook_connect_db.isDBConnected(connection));
    }
    @Test
    public void givenWrongJdbcUrlUsernamePassword_ShouldReturnFalse() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/addess_book?useSSl=false";
        String userNane = "root";
        String password = "robowars@1amit" ;
        Connection connection = addressBook_connect_db.getConnection(jdbcURL,userNane,password);
        Assert.assertFalse(addressBook_connect_db.isDBConnected(connection));
    }
    @Test
    public void  givenAddressBookDB_WhenRetrieved_ShouldMatchContactsCount() throws SQLException {
        Assert.assertEquals(2,addressBook_connect_db.readData().size());
    }
}
