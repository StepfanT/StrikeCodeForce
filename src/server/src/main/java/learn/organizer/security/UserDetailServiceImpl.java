package learn.organizer.security;

import learn.organizer.data.AppUserRepository;
import learn.organizer.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserDetailServiceImpl  implements UserDetailsService {
    AppUserRepository accounts;

    @Autowired
    public UserDetailServiceImpl(AppUserRepository accounts) {
        this.accounts = accounts;
    }


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser result = accounts.findByUsername(userName);
        if (result == null) {
            throw new UsernameNotFoundException(userName + " not found");
        }
        return result;
    }
}
