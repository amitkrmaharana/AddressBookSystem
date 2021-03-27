package addressbook;

import org.junit.Assert;
import org.junit.Test;

import java.net.CookieHandler;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

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
    @Test
    public void givenNewContactDetailToUpdateWithFirstName_WhenUpdated_ShouldSyncWithDB() {
        int result = addressBook_connect_db.updateContactDeatils("zip","560103","Manish");
        Assert.assertEquals(1,result);
    }
    @Test
    public void givenDateRange_WhenRetrived_ShouldMatchContactListCounts() {
        List<Contacts> contactList = addressBook_connect_db.reaadAndCountDateRangeDB("2015-01-01","2018-12-31");
        Assert.assertEquals(1,contactList.size());
    }
    @Test
    public void givenCity_WhenRetrived_ShouldReturnCountOfContactsInCity() {
        List<Contacts> contactList =addressBook_connect_db.getContactsByCity("Jamshedpur");
        Assert.assertEquals(1,contactList.size());
    }
    @Test
    public void givenNewContactDetails_WhenRetrived_ShouldReturnCountOFContacts() throws SQLException {
        List<Contacts> contactsList = addressBook_connect_db.addNewContact("Rishin","Chakravorty","friend","s-type","Howrah","West Bengal","711103","564525651","hgfvg@jdhgt.com","2020-03-14");
        Assert.assertEquals(3,contactsList.size());
    }
}
