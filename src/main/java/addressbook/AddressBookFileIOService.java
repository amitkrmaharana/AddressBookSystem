package addressbook;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AddressBookFileIOService {
    public static String ADDRESSBOOK_FILE_NAME = "addressBook_file.txt";

    public void write(List<Contacts> contactsList) {
        StringBuffer contactBuffer = new StringBuffer();
        contactsList.forEach(person ->{
            String contactDataString = person.toString().concat("\n");
            contactBuffer.append(contactDataString);
        });
        try {
            Files.write(Paths.get(ADDRESSBOOK_FILE_NAME),contactBuffer.toString().getBytes());
        }catch (IOException x){
            x.printStackTrace();
        }
    }

    public void printData() {
        try {
            Files.lines(new File(ADDRESSBOOK_FILE_NAME).toPath())
                    .forEach(System.out::println);
        }catch (IOException x){
            x.printStackTrace();
        }
    }

    public List<Contacts> readData() {
        List<Contacts> contactsList = new ArrayList();
        try {
            Files.lines(new File("addressBook_file.txt").toPath())
                    .map(line -> line.trim())
                    .forEach(line -> System.out.println(line));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contactsList;
    }
}
