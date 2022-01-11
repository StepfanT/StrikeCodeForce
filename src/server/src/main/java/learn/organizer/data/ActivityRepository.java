package learn.organizer.data;

import learn.organizer.models.Activity;

import java.util.List;

public interface ActivityRepository {

    List<Activity> getAllActivities();

    List<Activity> findByAppUserId(int userId);

    boolean addActivity(Activity activity);

    boolean editActivity(Activity activity);

    boolean deleteActivity(int activityId);

    boolean deleteAllActivityFromUser(int userId);

    Activity findActivityById(int activityId);
}