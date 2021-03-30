package addressbook;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    @Test
    public void givenNewContactDetails_WhenAdded_ShouldreturnEntriesUsingThread() {
        Contacts[] contactsArray = {
                new Contacts("Tom","Singhania","friend","p-type","Cuttack","Odisha","654258","8542578","hgfr@hgfb.com", "2021-03-05"),
                new Contacts("Jerry","Malhotra","friend","k-type","Bokaro","Jharkhand","658952","6658369","hgtd@jhg.com","2021-03-17"),
                new Contacts("Kajal","Mesharam","friend","k-type","Jamshedpur","Jharakhand","831004","65289456","ksju@jhy.com","2021-03-27"),
                new Contacts("Ravi","Kumar","family","p-type","Pune","Maharashtra","521669","6548552","ravi@gamjh.com","2021-03-28")
                };
        Instant start = Instant.now();
        addressBook_connect_db.addNewContactWithThreads(Arrays.asList(contactsArray));
        Instant end = Instant.now();
        System.out.println("Duration of the thread running is: " + Duration.between(start,end));
        List<Contacts> contactsList = addressBook_connect_db.readData();
        Assert.assertEquals(7,contactsList.size());
    }

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost/";
        RestAssured.port = 3000;
    }
    @Test
    public void givenContactdataInJsonServer_Whenretrived_ShouldMatchCount() {
        List<Contacts> contactsList = getContactList();
        Assert.assertEquals(2,contactsList.size());
    }

    private List<Contacts> getContactList() {
        Response response = RestAssured.get("/contacts");
        List<Contacts> contactsList = new Gson().fromJson(response.asString(),new TypeToken<List<Contacts>>(){}.getType());
        return contactsList;
    }

}
