package javawithdatabase.crudspringboot.service.sec;

import javawithdatabase.crudspringboot.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerUserDetails implements UserDetails {

    private final User user;
    public CustomerUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();


        switch (user.getRole().toUpperCase()) {
            case "ADMIN":
                authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                break;
            case "TEACHER":
                authorities.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
                break;
            case "HOD":
                authorities.add(new SimpleGrantedAuthority("ROLE_HOD"));
            default:
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
