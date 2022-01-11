package learn.organizer.data;

import learn.organizer.models.Activity;

import java.util.List;

public interface UserActivityRepository {

    List<Activity> findActivitiesFromAppUserId(int userId);

    boolean addUserToActivity(int userId,int activityId);

    boolean deleteUserFromActivity(int userId,int activityId);

    boolean deleteAllUserActivity(int userId);
}