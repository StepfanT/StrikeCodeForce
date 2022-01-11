package learn.organizer.data;

import learn.organizer.data.Mappers.ActivityMapper;
import learn.organizer.models.Activity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.sql.Date;
import java.util.List;

@Repository
public class ActivityJdbcTemplateRepository implements ActivityRepository {
    private JdbcTemplate jdbcTemplate;
    private ActivityMapper activityMapper;
    private UserActivityRepository userActivityRepository;

    public ActivityJdbcTemplateRepository(JdbcTemplate jdbcTemplate, ActivityMapper activityMapper,UserActivityRepository userActivityRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.activityMapper = activityMapper;
        this.userActivityRepository=userActivityRepository;
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
    @Transactional
    public boolean addActivity(Activity activity) {
        String sql = "insert into activity" +
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

        KeyHolder keyHolder = new GeneratedKeyHolder();
        boolean isAdded = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, activity.getActivityName());
            ps.setString(2, activity.getDescription());
            ps.setString(3, activity.getLocation());
            ps.setDate(4, Date.valueOf(activity.getDate()));
            ps.setString(5,activity.getTime());
            ps.setInt(6,activity.getUserId());
            ps.setInt(7,activity.getMax());
            ps.setInt(8,activity.getMin());
            ps.setString(9, activity.getCreateBy());
            //System.out.println("inside");
            return ps;
        }, keyHolder) > 0;

        if(isAdded){
            activity.setActivityId(keyHolder.getKey().intValue());
            System.out.println(activity.getActivityId());
            userActivityRepository.addUserToActivity(activity.getUserId(),activity.getActivityId());
        }

        return isAdded;
    }

    public boolean deleteAllActivityFromUser(int userId){
        String sql = "delete from activity " +
                "where userId = ?";
        return jdbcTemplate.update(sql, userId) > 0;
    }

    @Override
    public Activity findActivityById(int activityId) {
        final String sql="select * from activity " +
                "where activityId=?";
        return jdbcTemplate.query(sql,activityMapper,activityId).stream()
                .findFirst().orElse(null);
    }

    @Override
    public boolean deleteActivity(int id) {
        String sql = "delete from activity " +
                "where activityId = ?";
        return jdbcTemplate.update(sql, id) > 0;
    }


    @Override
    public boolean editActivity(Activity activity) {

        final String sql = "update activity set "
                + "activityName = ?, "
                + "description = ?, "
                + "location = ?, "
                + "date = ?, "
                + "time = ?, "
                + "minParticipant = ?, "
                + "maxParticipant = ? "
                + "where activityId = ?;";


        return jdbcTemplate.update(sql, activity.getActivityName(), activity.getDescription(),
                activity.getLocation(), activity.getDate(), activity.getTime(), activity.getMin(), activity.getMax(), activity.getActivityId()) > 0;
    }

}
