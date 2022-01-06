package learn.organizer.domain;

import learn.organizer.data.ActivityRepository;
import learn.organizer.models.Activity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class ActivityService {


    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository) {
        this.repository = repository;
    }

    public List<Activity> getAllActivities() {
        return repository.getAllActivities();
    }

    public List<Activity> findByAppUserId(int appUserId) {
        return repository.findByAppUserId(appUserId);
    }


    public Result<Activity> addActivity(Activity activity) {
        Result<Activity> result = validate(activity);
        if (!result.isSuccess()) {
            return result;
        }

        boolean isAdded = repository.addActivity(activity);
        result.setPayload(activity);
        if (isAdded != true) {
            result.addMessage("failed to add activity", ResultType.INVALID);
        }
        return result;
    }

    public Result<Activity> EditActivity(Activity activity) {
        Result<Activity> result = validate(activity);
        if (!result.isSuccess()) {
            return result;
        }

        if (activity.getActivityId() <= 0) {
            result.addMessage("activity id must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        if (!repository.editActivity(activity)) {
            String msg = String.format("activity id: %s, not found", activity.getActivityId());
            result.addMessage(msg, ResultType.NOT_FOUND);
        }

        return result;
    }

    public boolean deleteById(int activityId) {
        return repository.deleteActivity(activityId);
    }

    private Result<Activity> validate(Activity activity) {
        Result<Activity> result = new Result<>();
        if (activity == null) {
            result.addMessage("Activity name is required", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(activity.getDescription())) {
            result.addMessage("description is required", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(activity.getLocation())) {
            result.addMessage("location is required", ResultType.INVALID);
        }

        if (activity.getDate() != null && activity.getDate().isAfter(LocalDate.now())) {
            result.addMessage("activity date must be in the future", ResultType.INVALID);
        }
//  -ToDo: design way to enforce time input formatting and create validation if necessary

        String inputTimeString = activity.getTime();
        try {
            LocalTime.parse(inputTimeString);
            System.out.println("Valid time string: " + inputTimeString);
        } catch (DateTimeParseException | NullPointerException e) {
            System.out.println("Invalid time string: " + inputTimeString);
        }

        //max participant validation
        if (activity.getMax() > 100) {
            result.addMessage("number of participants cannot exceed 100", ResultType.INVALID);
        }
        //min participant validation
        if (activity.getMin() <= 0) {
            result.addMessage("number of participants must be greater than zero", ResultType.INVALID);
        }
        //contact validation not required?

        return result;
    }

}
