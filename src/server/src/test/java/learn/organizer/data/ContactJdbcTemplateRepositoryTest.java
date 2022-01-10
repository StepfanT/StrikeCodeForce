package learn.organizer.data;

import learn.organizer.domain.UserService;
import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ContactJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    ContactJdbcTemplateRepository repository;

    @Autowired
    UserService service;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}


    @Test
    void shouldGetContactByUserRambo() {
        Contact rambo = repository.getContactByUserId(2);
        assertEquals(2, rambo.getUserId());
        assertEquals("John", rambo.getFirstName() );
        assertEquals("Rambo", rambo.getLastName());
        assertEquals("The Forest", rambo.getLocation());

    }

//    @Test
//    void shouldAddContact() { //foreign key constraint
//        Contact contact = makeContact();
//        boolean actual = repository.addContact(contact);
//        assertNotNull(actual);

//    }

    @Test //bad sql grammar
    void shouldEditContact() {
        AppUser user = makeContact();
        user.getContact().setFirstName("Dan");
        assertTrue(repository.editContact(user.getContact()));
//        user.getContact().
//        assertFalse(repository.editContact(contact));
    }

    //do we ever delete a contact? probably not.


    private AppUser makeContact(){
        AppUser user = new AppUser("Chris", "1111");
        Contact contact = new Contact(1, "Jane", "Doe", "Jane@gmail", "Kenosha");
        user.setContact(contact);
        return user;
    }
}
