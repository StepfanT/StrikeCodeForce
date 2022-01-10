package learn.organizer.data;

import learn.organizer.models.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class ActivityJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    ActivityJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void shouldGetAllActivities() {
        List<Activity> activities = repository.getAllActivities();
        assertNotNull(activities);

        assertTrue(activities.size() >= 2 && activities.size() <= 6);
    }

    @Test
    void shouldFindActivityByUserId() {
       List<Activity> activity = repository.findByAppUserId(1);

       assertNotNull(activity);
       assertTrue(activity.size() >= 1);






    }

    @Test
    void shouldAddActivity() {
        // all fields
        Activity activity = makeActivity();
        boolean actual = repository.addActivity(activity);
        assertNotNull(actual);

        List<Activity> all = repository.getAllActivities();
        assertEquals(3, all.size());

        // null date
//        activity = makeActivity();
//        activity.setDate(null);
//        actual = repository.addActivity(activity);
//        assertNotNull(actual);
////        assertEquals(NEXT_ID + 1, actual.getActivityId());
    }


    @Test
    void shouldEditActivity() {
        Activity activity = makeActivity();
        activity.setActivityId(3);
        assertTrue(repository.editActivity(activity));
        activity.setActivityId(13);
        assertFalse(repository.editActivity(activity));

    }

    @Test
    void shouldDelete() {
        assertTrue(repository.deleteActivity(2));
        assertFalse(repository.deleteActivity(2));
    }

    private Activity makeActivity() {
        Activity activity = new Activity();
        activity.setActivityName("Test");
        activity.setLocation("location");
        activity.setDate(LocalDate.of(2022, 8, 8));
        activity.setTime("6:30pm");
        activity.setMax(20);
        activity.setMin(2);
        activity.setDescription("description");
        return activity;
    }



}
