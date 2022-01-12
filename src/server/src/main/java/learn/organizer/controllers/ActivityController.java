package learn.organizer.controllers;


import learn.organizer.domain.ActivityService;
import learn.organizer.domain.PointsService;
import learn.organizer.domain.Result;
import learn.organizer.domain.UserActivityService;
import learn.organizer.models.Activity;
import learn.organizer.models.AppUser;
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
    private final PointsService pointsService;

    public ActivityController(ActivityService service, UserActivityService userActivityService, PointsService pointsService) {
        this.activityService = service;
        this.userActivityService = userActivityService;
        this.pointsService = pointsService;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{activityId}")
    public Activity findByAppUserId(@PathVariable int activityId) throws DataAccessException {
        return activityService.findActivityById(activityId);
    }

    @GetMapping("/user/{userId}")
    public List<Activity> findActivitiesFromAppUserId(@PathVariable int userId) {
        return userActivityService.findActivitiesFromAppUserId(userId);
    }

    @GetMapping("/participants/{activityId}")
    public List<AppUser> findParticipantsFromActivity(@PathVariable int activityId) {
        return userActivityService.getUsersFromActivityId(activityId);
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
        System.out.println(activity.toString());
        Result<Activity> result = activityService.editActivity(activity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @PutMapping("/participants/{activityId}/{userId}")
    public ResponseEntity<Object> updateParticipant(@PathVariable int activityId,@PathVariable int userId) {

        Result<Boolean> result = pointsService.confirmPoints(activityId,userId);
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
