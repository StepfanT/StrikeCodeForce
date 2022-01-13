package learn.organizer.data;

import learn.organizer.data.Mappers.ContactMapper;
import learn.organizer.data.Mappers.PointsMapper;
import learn.organizer.models.Contact;
import learn.organizer.models.Points;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PointsJdbcTemplateRepository implements PointsRepository{
    private JdbcTemplate jdbcTemplate;
    private PointsMapper pointsMapper;

    public PointsJdbcTemplateRepository(JdbcTemplate jdbcTemplate, PointsMapper pointsMapper){
        this.jdbcTemplate=jdbcTemplate;
        this.pointsMapper=pointsMapper;
    }


    @Override
    public List<Points> getAllPointsByUserId(int userId) {
        String sql="select * from points " +
                "where userId=?";
        return jdbcTemplate.query(sql,pointsMapper,userId);
    }

    @Override
    public boolean addPoints(Points points) {
        String sql="insert into points (points,userId,activityId,activityCompleted) " +
                "values (?,?,?,?)";
        return jdbcTemplate.update(sql,points.getPoint(),points.getUserId(),points.getActivityId(),points.isCompleted())>0;
    }

    @Override
    public boolean editPoints(Points points) {
        String sql="update points " +
                "set points=?,userId=?,activityId=?,activityCompleted=? " +
                "where userId=? and activityId=?";
        return jdbcTemplate.update(sql,points.getPoint(),points.getUserId(),points.getActivityId(),points.isCompleted(),points.getUserId(),points.getActivityId())>0;

    }

    @Override
    public boolean deletePoints(int userId, int activityId) {
        String sql="delete from points " +
                "where userId=? and activityId=?";
        return jdbcTemplate.update(sql,userId,activityId)>0;
    }

    @Override
    public boolean deleteAllPointsFromUser(int userId){
        String sql="delete from points " +
                "where userId=?";
        return jdbcTemplate.update(sql,userId)>0;
    }

    @Override
    public boolean confirmPoints(int activityId,int userId){
        String sql="update points " +
                "set activityCompleted=true " +
                "where activityId=? and userId=?;";
        return jdbcTemplate.update(sql,activityId,userId)>0;
    }

    @Override
    public boolean deleteAllActivityPoints(int activityId){
        String sql="delete from points " +
                "where activityId=?";
        return jdbcTemplate.update(sql,activityId)>0;
    }
}
