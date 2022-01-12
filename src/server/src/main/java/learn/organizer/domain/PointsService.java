package learn.organizer.domain;

import learn.organizer.data.ContactRepository;
import learn.organizer.data.PointsRepository;
import learn.organizer.models.Contact;
import learn.organizer.models.Points;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsService {
    //validate contact

    private final PointsRepository repository;

    public PointsService(PointsRepository repository) {
        this.repository = repository;
    }

    public List<Points> getPointsByUserId(int userId){
        return repository.getAllPointsByUserId(userId);
    }

    public Result<Points> addPoints(Points points){
        Result<Points> result=new Result<>();
        result.setPayload(points);
        result=validate(points);
        if(result.isSuccess()){
            if(repository.addPoints(points)){
                result.addMessage("Error adding points",ResultType.INVALID);
            }
        }

        return result;
    }


    public Result<Points> editPoints(Points points){
        Result<Points> result=new Result<>();
        result.setPayload(points);
        if(!repository.editPoints(points)){
            result.addMessage("Failed to edit points",ResultType.INVALID);
        }
        return result;
    }

    public Result<Points> deletePoints(Points points){
        Result<Points> result=new Result<>();
        result.setPayload(points);
        if(!repository.editPoints(points)){
            result.addMessage("Failed to delete points",ResultType.INVALID);
        }
        return result;
    }

    public Result<Boolean> deleteAllPointsFromUser(int userId){
        Result<Boolean> result=new Result<>();
        boolean isDeleted=repository.deleteAllPointsFromUser(userId);
        result.setPayload(isDeleted);
        if(!isDeleted){
            result.addMessage("Failed to delete all points",ResultType.INVALID);
        }
        return result;
    }

    public Result<Boolean> confirmPoints(int activityId,int userId){
        Result<Boolean> result=new Result<>();
        boolean isConfirmed=repository.confirmPoints(activityId,userId);
        result.setPayload(isConfirmed);
        if(!isConfirmed){
            result.addMessage("Failed to confirm points",ResultType.INVALID);
        }
        return result;
    }

    public Result<Points> validate(Points points){
        Result<Points> result=new Result<>();
        if(points.getPoint()==0){
            result.addMessage("Input a number greater than 0",ResultType.INVALID);
        }
        if(points.getActivityId()==-1){
            result.addMessage("Missing valid activity id",ResultType.INVALID);
        }
        if(points.getUserId()==-1){
            result.addMessage("Missing valid user id",ResultType.INVALID);
        }
        return result;
    }
}
