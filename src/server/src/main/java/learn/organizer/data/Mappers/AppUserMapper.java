package learn.organizer.data.Mappers;


import learn.organizer.data.ContactRepository;
import learn.organizer.models.AppRole;
import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AppUserMapper implements RowMapper<AppUser> {
    ContactRepository contactRepository;

    @Autowired
    public AppUserMapper(ContactRepository contactRepository){this.contactRepository=contactRepository;}

    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        int appUserId = resultSet.getInt("userId");
        String username = resultSet.getString("username");
        String passwordHash = resultSet.getString("password");

        List<AppRole> roles = List.of(new AppRole(resultSet.getString("userRole")));

        Contact contact= contactRepository.getContactByUserId(appUserId);

        AppUser appUser = new AppUser(appUserId, username, passwordHash, false, roles,contact);

        return appUser;
    }
}
