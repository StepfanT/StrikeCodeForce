package learn.organizer.data;

import learn.organizer.models.AppUser;
import learn.organizer.models.Contact;
import learn.organizer.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
    void shouldFindAll() {
        List<AppUser> appUsers = repository.findAll();
        assertNotNull(appUsers);

        assertTrue(appUsers.size() >= 1 && appUsers.size() <= 6);
    }

    @Test
    void ShouldFindById() {
        AppUser loneWolf = repository.findById(2);
        assertEquals(2, loneWolf.getAppUserId());
        assertEquals("loneWolf", loneWolf.getUsername());
    }

    @Test
    void shouldFindByUserName() {
        AppUser frigiid = repository.findByUsername("frigiid");
        assertEquals(1, frigiid.getAppUserId());
        assertEquals("frigiid", frigiid.getUsername());

    }

    @Test // issues with setting AppUser constructor and using user superclass(no methods)
    void shouldAddUser() {

          AppUser user = makeAppUser();
          AppUser actual = repository.add(user);
          assertNotNull(actual);
          assertEquals(NEXT_ID, actual.getAppUserId());

    }

    @Test
    void shouldUpdateUser() {
        AppUser user = makeAppUser();
        user.setAppUserId(2);
        assertTrue(repository.update(user));
    }

    @Test //cannot delete or update a parent row: foreign key constraint fails
    void shouldDeleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }


    AppUser makeAppUser() {
        AppUser user = new AppUser("test", "12345");
        Contact contact = new Contact(-1, "Jane", "Doe", "Jane@gmail.com", "Mexico");
        user.setContact(contact);
        user.setAppUserId(4);
        return user;
  }



}
