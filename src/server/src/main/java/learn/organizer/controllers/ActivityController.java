package learn.organizer.controllers;


import learn.organizer.domain.ActivityService;
import learn.organizer.domain.Result;
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

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Activity> getAllActivities() {
        return service.getAllActivities();
    }


    @GetMapping("/{userId}")
    public List<Activity> findByAppUserId(@PathVariable int userId) throws DataAccessException {
        return service.findByAppUserId(userId);
    }

    @PostMapping
    public ResponseEntity<Object> addActivity(@RequestBody Activity activity) {
        Result<Activity> result = service.addActivity(activity);
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

        Result<Activity> result = service.EditActivity(activity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{activityId}")
    public ResponseEntity<Void> deleteById(@PathVariable int activityId) {
        if (service.deleteById(activityId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
