package learn.organizer.controllers;


import learn.organizer.domain.ActivityService;
import learn.organizer.domain.Result;
import learn.organizer.domain.UserActivityService;
import learn.organizer.models.Activity;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/activity")
public class ActivityController {

    private final ActivityService activityService;
    private final UserActivityService userActivityService;

    public ActivityController(ActivityService service, UserActivityService userActivityService) {
        this.activityService = service;
        this.userActivityService = userActivityService;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{userId}")
    public List<Activity> findByAppUserId(@PathVariable int userId) throws DataAccessException {
        return activityService.findByAppUserId(userId);
    }

    @GetMapping("/user/{userId}")
    public List<Activity> findActivitiesFromAppUserId(@PathVariable int userId) {
        return userActivityService.findActivitiesFromAppUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Object> addActivity(@RequestBody Activity activity) {
        System.out.println(activity.toString());
        Result<Activity> result = activityService.addActivity(activity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }


    @PostMapping("/user/{userId}/{activityId}")
    public ResponseEntity<Object> addUserToActivity(@PathVariable int userId,@PathVariable int activityId){
        Result<Boolean> result = userActivityService.addUserToActivity(userId,activityId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }


    @PutMapping("/{activityId}")
    public ResponseEntity<Object> update(@PathVariable int activityId, @RequestBody Activity activity) {
        if (activityId != activity.getActivityId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Activity> result = activityService.EditActivity(activity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteById(@PathVariable int activityId) {
        if (activityService.deleteById(activityId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{userId}/{activityId}")
    public ResponseEntity<Void> deleteUserFromActivity(@PathVariable int userId,@PathVariable int activityId) {
        Result<Boolean> result =userActivityService.deleteUserFromActivity(userId,activityId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteAllUserActivity(@PathVariable int userId) {
        Result<Boolean> result =userActivityService.deleteAllUserActivity(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
