package learn.organizer.controllers;

import learn.organizer.domain.ActivityService;
import learn.organizer.models.Activity;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/authenticate")
public class ActivityController {

    private final ActivityService service;

    public ActivityController(ActivityService service) {
        this.service = service;
    }

    //add GetMapping annotation
    public List<Activity> findAll() {return service.findAll();}


    //add PostMapping annotation
    public List<Activity> findByAppUserId(int userId) throws DataAccessException {
        Activity activities = service.findByAppUserId(userId);
        if(activities == null){

        }
        return null;
    }
}
