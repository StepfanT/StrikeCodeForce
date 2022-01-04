package learn.organizer.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AppUser extends User {
    private int appUserId;
    private String role;
    private Contact contact;
    public AppUser(int appUserId, String username, String password, boolean disabled, List<AppRole> roles,Contact contact) {
        super(username, password, !disabled,
                true, true, true,
                convertRolesToAuthorities(roles));
        this.appUserId = appUserId;
        this.role=roles.get(0).getName();
        this.contact=contact;
    }

    public AppUser(String username,String password){
        super(username, password, false,
                true, true, true,
                convertRolesToAuthorities(List.of(new AppRole("USER"))));
        this.appUserId =0;
    }
    private static Collection<? extends GrantedAuthority> convertRolesToAuthorities(List<AppRole> roles) {
        List<GrantedAuthority> result = new ArrayList<>();
        for (AppRole role : roles) {
            result.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return result;
    }

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppUser appUser = (AppUser) o;
        return appUserId == appUser.appUserId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), appUserId);
    }

    public String getRole() {
        return role;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }


    public Contact getContact() {
        return contact;
    }
}
