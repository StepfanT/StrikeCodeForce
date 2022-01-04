package learn.organizer.data;

import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.models.Activity;
import learn.organizer.models.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactJdbcTemplateRepository implements ContactRepository{
    private JdbcTemplate jdbcTemplate;
    private ActivityMapper activityMapper;

    public ContactJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ActivityMapper activityMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.activityMapper=activityMapper;
    }


    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public Contact getContactByUserId(int userId) {
        return null;
    }

    @Override
    public boolean addContact(Contact contact) {
        return false;
    }

    @Override
    public boolean addContact(int id) {
        return false;
    }

    @Override
    public boolean editActivity(Contact contact) {
        return false;
    }
}
