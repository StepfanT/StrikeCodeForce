package learn.organizer.domain;

import learn.organizer.data.ContactJdbcTemplateRepository;
import learn.organizer.data.ContactRepository;
import learn.organizer.models.Contact;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ContactServiceTest {
    @Autowired
    ContactService service;

    @MockBean
    ContactRepository contactRepository;

    @Test
    void shouldGetContactByUserId(){
        Contact contact = new Contact(1, "Stepfan", "Thelemaque", null, "Milwaulkee");

        when(contactRepository.getContactByUserId(1)).thenReturn(contact);
        Contact stepfan = service.getContactByUserId(1);

        assertEquals("Stepfan", stepfan.getFirstName());

    }
    @Test
    void shouldEditContact(){
        Contact contact = new Contact(5, "TestFirst", "TestLast", "Test.gmail", "TestLocation");

        when(contactRepository.editContact(contact)).thenReturn(true);
        Result<Contact> actual = service.editContact(contact);
        assertEquals(ResultType.SUCCESS, actual.getType());
    }

    @Test
    void shouldNotEditMissing(){
        Contact contact = new Contact(45, "Test", "TestLast", "Test.gmail", "TestLocation");

        when(contactRepository.editContact(contact)).thenReturn(false);
        Result<Contact> actual = service.editContact(contact);
        assertEquals(ResultType.INVALID, actual.getType());

    }

    @Test
    void shouldNotEditWhenInvalid(){
        Contact contact = new Contact(45, "Test", "TestLast", "Test.gmail", "TestLocation" );

        Result<Contact> actual = service.editContact(contact);
        assertEquals(ResultType.INVALID, actual.getType());

        contact.setFirstName("Test");
        contact.setLastName(" ");
        actual = service.editContact(contact);
        assertEquals(ResultType.INVALID, actual.getType());

        contact.setUserId(0);
        contact.setLastName("Last Name Test");
        actual = service.editContact(contact);
        assertEquals(ResultType.INVALID, actual.getType());
    }
}
