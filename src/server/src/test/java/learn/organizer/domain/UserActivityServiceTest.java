package learn.organizer.domain;

import learn.organizer.data.UserActivityRepository;
import learn.organizer.data.UserRepository;
import learn.organizer.models.Activity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserActivityServiceTest {

    @Autowired
    UserActivityService service;

    @MockBean
    UserActivityRepository repository;

    @Test
    void shouldFindActivitiesFromAppUserId(){
        List<Activity> mockActivityList=new ArrayList<>();
        mockActivityList.add(new Activity());
        mockActivityList.add(new Activity());
        mockActivityList.add(new Activity());
        when(repository.findActivitiesFromAppUserId(1)).thenReturn(mockActivityList);
        List<Activity> activityList=repository.findActivitiesFromAppUserId(1);

        assertEquals(3,activityList.size());
    }

    @Test
    void shouldAddUserToActivity(){

        when(repository.addUserToActivity(1,3)).thenReturn(true);

        assertEquals(true,repository.addUserToActivity(1,3));
    }

    @Test
    void shouldDeleteUserFromActivity(){
        when(repository.deleteUserFromActivity(1,3)).thenReturn(true);

        assertEquals(true,repository.deleteUserFromActivity(1,3));
    }

    @Test
    void shouldDeleteAllUserActivity(){
        when(repository.deleteAllUserActivity(1)).thenReturn(true);

        assertEquals(true,repository.deleteAllUserActivity(1));
    }
}
