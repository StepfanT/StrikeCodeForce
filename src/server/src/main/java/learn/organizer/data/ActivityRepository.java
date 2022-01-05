package learn.organizer.data;

import learn.organizer.models.Activity;

import java.util.List;

public interface ActivityRepository {

    public List<Activity> getAllActivities();
    public boolean addActivity(Activity activity);
    public boolean deleteActivity(int id);
    public boolean editActivity(Activity activity);

}
