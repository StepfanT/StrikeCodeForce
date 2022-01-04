package learn.organizer.data.Mappers;

import learn.organizer.models.Activity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class ActivityMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
        Activity activity = new Activity();

        return activity;
    }
}
