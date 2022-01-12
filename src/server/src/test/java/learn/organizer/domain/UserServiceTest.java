package learn.organizer.domain;

import learn.organizer.data.AppUserRepository;
import learn.organizer.domain.exceptions.AccountCreationException;
import learn.organizer.models.AppUser;
import learn.organizer.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    AppUserRepository repository;

    @Test//is this test actually testing if new account is being created?
    void shouldCreateAccount() throws AccountCreationException {
        AppUser user = makeUser();
        AppUser mockOut = makeUser();
        mockOut.setAppUserId(4);

        when(repository.add(user)).thenReturn(mockOut);

        AppUser actual = service.createAccount(user);
        assertEquals(4, actual.getAppUserId());



    }



    AppUser makeUser() {
        AppUser user = new AppUser("Tim", "000");
        return user;
//        user.setUser

    }
}
