package learn.organizer.domain;

import learn.organizer.data.ActivityRepository;
import learn.organizer.models.Activity;

import java.util.List;

public class ActivityService {

    private final ActivityRepository repository;

    public ActivityService(ActivityRepository repository){this.repository = repository;}

    public List<Activity> findAll() {return repository.findAll();}

    public Activity findByAppUserId(int appUserId) {return repository.findByAppUserId(appUserId);}


}
