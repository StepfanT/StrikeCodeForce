package learn.organizer.data;

import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.data.Mappers.ContactMapper;
import learn.organizer.models.Activity;
import learn.organizer.models.Contact;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactJdbcTemplateRepository implements ContactRepository{
    private JdbcTemplate jdbcTemplate;
    private ContactMapper contactMapper;

    public ContactJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ContactMapper contactMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.contactMapper=contactMapper;
    }


    @Override
    public List<Contact> getAllContacts() {
        return null;
    }

    @Override
    public Contact getContactByUserId(int userId) {
        String sql= "select * from contact " +
                "where userId=?";
        return jdbcTemplate.queryForObject(sql,contactMapper,userId);

    }

    @Override
    public boolean addContact(Contact contact) {
        String sql="insert into contact (userId,firstName,lastName,email,location) " +
                "values (?,?,?,?,?)";
        return jdbcTemplate.update(sql,contact.getUserId(),contact.getFirstName(),contact.getLastName(),contact.getEmail(),contact.getLocation())>0;
    }

    @Override
    public boolean editContact(Contact contact) {
        String sql="update contact " +
                "set firstName=?,lastName=?,email=?,location=? " +
                "where userId=? ";
        return jdbcTemplate.update(sql,contact.getFirstName(),contact.getLastName(),contact.getEmail(),contact.getLocation(),contact.getUserId())>0;

    }
}
