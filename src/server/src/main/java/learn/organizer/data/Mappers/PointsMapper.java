package learn.organizer.data.Mappers;

import learn.organizer.models.Contact;
import learn.organizer.models.Points;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class PointsMapper implements RowMapper<Points> {

    @Override
    public Points mapRow(ResultSet resultSet, int i) throws SQLException {
        int point=resultSet.getInt("points");
        int userId = resultSet.getInt("userId");
        int activityId=resultSet.getInt("activityId");
        boolean activityCompleted = resultSet.getBoolean("activityCompleted");

        return new Points(point,userId,activityId,activityCompleted);
    }

}
