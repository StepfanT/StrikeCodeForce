package learn.organizer.data.Mappers;

import learn.organizer.models.Activity;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ActivityMapper implements RowMapper<Activity> {

    @Override
    public Activity mapRow(ResultSet resultSet, int i) throws SQLException {
        Activity activity = new Activity();
        activity.setActivityId(resultSet.getInt("activityId"));
        activity.setActivityName(resultSet.getString("activityName"));
        activity.setDescription(resultSet.getString("description"));
        activity.setLocation(resultSet.getString("location"));
        activity.setMin(resultSet.getInt("min"));
        activity.setMax(resultSet.getInt("max"));
        activity.setUserId(resultSet.getInt("userId"));
        activity.setDate(LocalDate.parse(resultSet.getString("date")));
//        activity.setTime(LocalTime.parse(resultSet.getString("time")));

        return activity;
    }
}
