package learn.organizer.domain;

import learn.organizer.data.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class UserActivityServiceTest {

    @Autowired
    UserActivityService service;

    @MockBean
    UserRepository repository;

    @Test
    void shouldFindActivitiesFromAppUserId(){

    }

    @Test
    void shouldSddUserToActivity(){

    }

    @Test
    void shouldDeleteUserFromActivity(){

    }

    @Test
    void shouldDeleteAllUserActivity(){

    }
}
