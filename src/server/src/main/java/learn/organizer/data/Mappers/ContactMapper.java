package learn.organizer.data.Mappers;

import learn.organizer.models.Activity;
import learn.organizer.models.AppRole;
import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class ContactMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet resultSet, int i) throws SQLException {
        int userId = resultSet.getInt("userId");
        String firstName = resultSet.getString("firstName");
        String lastName = resultSet.getString("lastName");
        String email = resultSet.getString("email");
        String location = resultSet.getString("userLocation");

        return new Contact(userId,firstName,lastName,email,location);
    }
}
