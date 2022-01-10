package learn.organizer.controllers;


import learn.organizer.domain.ContactService;
import learn.organizer.domain.PointsService;
import learn.organizer.domain.Result;
import learn.organizer.models.Contact;
import learn.organizer.models.Points;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("api/points")
public class PointsController {

    private final PointsService service;

    public PointsController(PointsService service) {
        this.service = service;
    }


    @GetMapping("/{userId}")
    public List<Points> getAllUserPoints(@PathVariable int userId) throws DataAccessException {
        return service.getPointsByUserId(userId);
    }


    @PutMapping("/{userId}/{activityId}")
    public ResponseEntity<Object> update(@PathVariable int userId,@PathVariable int activityId ,@RequestBody Points points) {
        if (userId != points.getUserId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Points> result = service.editPoints(points);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Points points){
        Result<Points> result = service.addPoints(points);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping
    public ResponseEntity<Object> delete(@RequestBody Points points){
        Result<Points> result = service.deletePoints(points);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteAllUserPoints(@PathVariable int userId){
        Result<Boolean> result = service.deleteAllPointsFromUser(userId);
        if (result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }
}
