package learn.organizer.data;

import learn.organizer.models.AppUser;
import learn.organizer.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AppUserRepository {
    List<AppUser> findAll();
    AppUser findById(int accountId);
    AppUser findByUsername(String username);

    @Transactional
    AppUser add(AppUser appUser);
    @Transactional
    boolean update(AppUser appUser);
    @Transactional
    boolean deleteById(int account);
}
