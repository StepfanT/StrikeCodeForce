package learn.organizer.domain;

import learn.organizer.data.PointsRepository;
import learn.organizer.data.UserActivityRepository;
import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;
import learn.organizer.models.Points;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserActivityService {
    //validate contact

    private final UserActivityRepository repository;

    public UserActivityService(UserActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> findActivitiesFromAppUserId(int userId){
        return repository.findActivitiesFromAppUserId(userId);
    }

    public List<AppUser> getUsersFromActivityId(int activityId){
        return repository.getUsersFromActivityId(activityId);
    }

    public Result<Boolean> addUserToActivity(int userId,int activityId){
        Result<Boolean> result=new Result<>();
        boolean isAdded=repository.addUserToActivity(userId,activityId);
        result.setPayload(isAdded);
        if(!isAdded){
            result.addMessage("Failed to add user to activity",ResultType.INVALID);
        }

        return result;
    }

    public Result<Boolean> deleteUserFromActivity(int userId,int activityId){
        Result<Boolean> result=new Result<>();
        boolean isDeleted=repository.deleteUserFromActivity(userId,activityId);
        result.setPayload(isDeleted);
        if(!isDeleted){
            result.addMessage("Failed to delete user from activity",ResultType.INVALID);
        }

        return result;
    }

    public Result<Boolean> deleteAllUserActivity(int userId){
        Result<Boolean> result=new Result<>();
        boolean isDeleted=repository.deleteAllUserActivity(userId);
        result.setPayload(isDeleted);
        if(!isDeleted){
            result.addMessage("Failed to delete user from all activities",ResultType.INVALID);
        }
        return result;
    }

}
