package learn.organizer.data;

import learn.organizer.models.Activity;

import java.util.List;

public interface ActivityRepository {
    List<Activity> findAll();

    Activity findByAppUserId(int userId);

    Activity add(Activity activity);

    boolean update(Activity activity);

    boolean deleteById(int activityId);
}
