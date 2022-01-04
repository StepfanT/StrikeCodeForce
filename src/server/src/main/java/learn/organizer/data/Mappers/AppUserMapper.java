package learn.organizer.data.Mappers;


import learn.organizer.models.AppRole;
import learn.organizer.models.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class AppUserMapper implements RowMapper<AppUser> {
    @Override
    public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
        int appUserId = resultSet.getInt("userId");
        String username = resultSet.getString("username");
        String passwordHash = resultSet.getString("password");
        boolean disabled = false;

        List<AppRole> roles = List.of(new AppRole(resultSet.getString("userRole")));

        AppUser appUser = new AppUser(appUserId, username, passwordHash, disabled, roles);

        return appUser;
    }
}
