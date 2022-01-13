package learn.organizer.data;

import learn.organizer.models.Activity;
import learn.organizer.models.Points;

import java.util.List;

public interface PointsRepository {

    List<Points> getAllPointsByUserId(int userId);

    boolean addPoints(Points points);

    boolean editPoints(Points points);

    boolean deletePoints(int userId,int activityId);

    boolean deleteAllActivityPoints(int activityId);

    boolean deleteAllPointsFromUser(int userId);

    boolean confirmPoints(int activityId,int userId);
}