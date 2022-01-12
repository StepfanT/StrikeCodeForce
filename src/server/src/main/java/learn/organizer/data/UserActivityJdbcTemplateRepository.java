package learn.organizer.data;


import learn.organizer.App;
import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.data.Mappers.AppUserMapper;
import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;
import learn.organizer.models.Points;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserActivityJdbcTemplateRepository implements UserActivityRepository{
    private JdbcTemplate jdbcTemplate;
    private ActivityMapper activityMapper;
    private AppUserMapper appUserMapper;
    private PointsRepository pointsRepository;

    public UserActivityJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ActivityMapper activityMapper,AppUserMapper appUserMapper,PointsRepository pointsRepository){
        this.jdbcTemplate=jdbcTemplate;
        this.activityMapper=activityMapper;
        this.pointsRepository=pointsRepository;
        this.appUserMapper=appUserMapper;
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

    @Override
    public List<AppUser> getUsersFromActivityId(int activityId){
        final String sql="select username,u.userId from user_activity as ua " +
                "inner join user as u on ua.userId=u.userId " +
                "where activityId=?";
        return jdbcTemplate.query(sql, new RowMapper<AppUser>() {
            @Override
            public AppUser mapRow(ResultSet resultSet, int i) throws SQLException {
                AppUser user=new AppUser(resultSet.getString("username"),"");
                user.setAppUserId(resultSet.getInt("userId"));

                return user;
            }
        },activityId);

    }
}
