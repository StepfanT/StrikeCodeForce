package learn.organizer.data;

import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;

import java.util.List;
import java.util.Map;

public interface UserActivityRepository {

    List<Activity> findActivitiesFromAppUserId(int userId);

    boolean addUserToActivity(int userId,int activityId);

    boolean deleteUserFromActivity(int userId,int activityId);

    boolean deleteAllFromActivityId(int activityId);

    boolean deleteAllUserActivity(int userId);

    List<AppUser> getUsersFromActivityId(int activityId);
}