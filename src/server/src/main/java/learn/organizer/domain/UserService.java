package learn.organizer.domain;

import learn.organizer.data.AppUserRepository;
import learn.organizer.domain.exceptions.AccountCreationException;
import learn.organizer.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    AppUserRepository appUsers;
    @Autowired
    public UserService(AppUserRepository appUsers, PasswordEncoder passwordEncoder) {
        this.appUsers = appUsers;
    }

    public AppUser createAccount(AppUser appUser) throws AccountCreationException {
        if (appUsers.findByUsername(appUser.getUsername()) != null) {

                System.out.println("found");
            throw new AccountCreationException("Username already exists");
        }

        try {
            appUser= appUsers.add(appUser);
            return appUser;
        } catch (Exception e) {
            throw new AccountCreationException("Unspecified error occurred");
        }

    }

}
