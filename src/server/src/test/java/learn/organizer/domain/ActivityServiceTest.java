package learn.organizer.domain;

import learn.organizer.data.ActivityRepository;
import learn.organizer.data.KnownGoodState;
import learn.organizer.models.Activity;
import learn.organizer.models.Contact;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    ActivityService service;

    @MockBean
    ActivityRepository repository;



    @Test
    void ShouldFindOneActivity() {
        // pass-through test, probably not useful
        List<Activity> activity = service.findByAppUserId(1);
        assertEquals(1, activity.size());
    }

    @Test //foreign key constraint fails
    void shouldNotAddWhenInvalid() {
        Activity activity = makeActivity();
        Result<Activity> result = service.addActivity(activity);
        assertEquals(ResultType.INVALID, result.getType());

        activity.setActivityId(0);
        activity.setActivityName(null);
        result = service.addActivity(activity);
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddWhenValid() {
        Activity expected = makeActivity();
        Activity arg = makeActivity();
        arg.setActivityId(0);

    }

    Activity makeActivity(){
        Activity activity = new Activity();
        activity.setActivityId(4);
        activity.setActivityName("Basket Weaving");
        activity.setDescription("Underwater basket weaving, 1v1");
        activity.setLocation("Lake Michigan");
        activity.setDate(LocalDate.of(2022, 2, 2));
        activity.setTime("5:30");
        activity.setMin(2);
        activity.setMax(2);
        activity.setCreateBy("Steve");
        return activity;

    }
}
