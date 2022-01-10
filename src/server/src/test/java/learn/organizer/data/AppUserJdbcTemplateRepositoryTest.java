package learn.organizer.data;

import learn.organizer.models.AppUser;
import learn.organizer.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AppUserJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 4;

    @Autowired
    AppUserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() {
        knownGoodState.set();
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByUsername() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }
    //?make user or make AppUser(issues with appUser constructor)
    private AppUser makeAppUser() {
        User User = new User();
        return null;
    }

}
