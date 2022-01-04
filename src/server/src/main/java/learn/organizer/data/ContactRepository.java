package learn.organizer.data;

import learn.organizer.models.Activity;
import learn.organizer.models.Contact;

import java.util.List;

public interface ContactRepository {

    public List<Contact> getAllContacts();
    public Contact getContactByUserId(int userId);
    public boolean addContact(Contact contact);
    public boolean editContact(Contact contact);

}
