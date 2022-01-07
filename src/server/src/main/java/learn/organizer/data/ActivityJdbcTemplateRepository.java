package learn.organizer.data;

import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.models.Activity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;
import learn.organizer.models.Activity;
import java.util.List;

@Repository
public class ActivityJdbcTemplateRepository implements ActivityRepository{
    private JdbcTemplate jdbcTemplate;
    private ActivityMapper activityMapper;

    public ActivityJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ActivityMapper activityMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.activityMapper=activityMapper;
    }

    @Override
    public List<Activity> getAllActivities() {
        String sql = "select * from activity";
        return jdbcTemplate.query(sql, activityMapper);
    }

    @Override
    public List<Activity> findByAppUserId(int appUserId) {


        final String sql = "select * "
                + "from activity "
                + "where userId = ?";

        List<Activity> result = jdbcTemplate.query(sql, new ActivityMapper(), appUserId);


        return result;
    }

    @Override
    public boolean addActivity(Activity activity) {
        String sql="insert into activity"+
                "(activityName," +
                "description," +
                "location," +
                "date," +
                "time," +
                "userId," +
                "maxParticipant," +
                "minParticipant," +
                "createBy) " +
                "values(?,?,?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql,activity.getActivityName(),activity.getDescription(),activity.getLocation()
        ,activity.getDate(),activity.getTime(),activity.getUserId(),activity.getMax(),activity.getMin(),activity.getCreateBy())>0;
    }

    @Override
    public boolean deleteActivity(int id) {
        String sql="delete from activity" +
                "where activityId=?";
        return jdbcTemplate.update(sql,id)>0;
    }

    //change editActivity to match AppUser repository method
    @Override
    public boolean editActivity(Activity activity) {

        final String sql = "update activity set "
                + "activityName = ?, "
                + "description = ?, "
                + "location = ?, "
                + "date = ?, "
                + "time = ?, "
                + "minParticipant = ?, "
                + "maxParticipant = ?, ";


        return jdbcTemplate.update(sql, activity.getActivityName(), activity.getDescription(),
                activity.getLocation(), activity.getDate(), activity.getTime(), activity.getMin(), activity.getMax()) > 0;
    }
}
