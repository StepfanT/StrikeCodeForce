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
@RequestMapping("/authenticate")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    @GetMapping
    public List<Activity> findAll() {return service.findAll();}


    @GetMapping("/{agencyId}")
    public ResponseEntity<Activity> findByAppUserId(@PathVariable int userId) throws DataAccessException {
        Activity activity = service.findByAppUserId(userId);
        if(activity == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(activity);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Activity activity) {
        Result<Activity> result = service.add(activity);
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

        Result<Activity> result = service.update(activity);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{agentId}")
    public ResponseEntity<Void> deleteById(@PathVariable int agentId) {
        if (service.deleteById(agentId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

