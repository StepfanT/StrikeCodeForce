package learn.organizer.data;

import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.data.Mappers.PointsMapper;
import learn.organizer.models.Activity;
import learn.organizer.models.Points;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserActivityJdbcTemplateRepository implements UserActivityRepository{
    private JdbcTemplate jdbcTemplate;
    private ActivityMapper activityMapper;
    private PointsRepository pointsRepository;

    public UserActivityJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ActivityMapper activityMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.activityMapper=activityMapper;
    }


    @Override
    public List<Activity> findActivitiesFromAppUserId(int userId) {
        final String sql="select * from user_activity as ua " +
                "inner join activity as a on ua.activityId=a.activityId " +
                "where ua.userId=?;";
        return jdbcTemplate.query(sql,activityMapper,userId);

    }

    @Override
    @Transactional
    public boolean addUserToActivity(int userId, int activityId) {
        final String sql="insert into user_activity " +
                "values (?,?)";
        Points points=new Points(1,userId,activityId,false);
        boolean isPointsAdded = pointsRepository.addPoints(points);
        return jdbcTemplate.update(sql,userId,activityId)>0;
    }

    @Override
    public boolean deleteUserFromActivity(int userId, int activityId) {
        final String sql="delete from user_activity " +
                "where userId=? and activityId=?";

        return jdbcTemplate.update(sql,userId,activityId)>0;
    }

    @Override
    public boolean deleteAllUserActivity(int userId) {
        final String sql="delete from user_activity " +
                "where userId=?";
        return jdbcTemplate.update(sql,userId)>0;
    }
}
