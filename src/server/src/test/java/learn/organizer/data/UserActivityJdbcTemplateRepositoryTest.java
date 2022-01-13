package learn.organizer.data;

import learn.organizer.models.Activity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserActivityJdbcTemplateRepositoryTest {

    @Autowired
    UserActivityJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {knownGoodState.set();}

    @Test
    void shouldFindEnrolledActivitiesFromAppUserId(){
        List<Activity> activity = repository.findActivitiesFromAppUserId(1);
        assertEquals(2, activity.size());
    }

    @Test
    void shouldAddUserToActivity(){
        boolean added=repository.addUserToActivity(2,1);
        assertTrue(added);

    }

    @Test
    void shouldDeleteUserFromActivity(){
        boolean deleted=repository.deleteUserFromActivity(1,1);
        assertTrue(deleted);

        deleted=repository.deleteUserFromActivity(1,1);
        assertFalse(deleted);
    }

    @Test
    void shouldDeleteAllUserActivity(){
        boolean deleted=repository.deleteAllUserActivity(1);
        assertTrue(deleted);
    }


}
