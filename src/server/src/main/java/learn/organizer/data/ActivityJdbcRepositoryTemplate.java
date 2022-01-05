package learn.organizer.data;

import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;
import org.springframework.dao.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class ActivityJdbcRepositoryTemplate implements ActivityRepository{
    @Override
    public List<Activity> findAll() {
        return null;
    }

    @Override
    public Activity findByAppUserId(int userId) {
        return null;

    }

    @Override
    public Activity add(Activity activity) {
        return null;
    }

    @Override
    public boolean update(Activity activity) {
        return false;
    }

    @Override
    public boolean deleteById(int activityId) {
        return false;
    }
}
