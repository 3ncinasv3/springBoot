package ca.sheridancollege.BookStoreAssignmentencinasv.security;

import ca.sheridancollege.BookStoreAssignmentencinasv.database.DatabaseAccess;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final DatabaseAccess da;

    public UserDetailsServiceImpl(DatabaseAccess da) {
        this.da = da;
    }


    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
        ca.sheridancollege.BookStoreAssignmentencinasv.beans.User user = da.findUserAccount(username);
        if (user == null) {
            System.out.println("User not found:" + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the the db");
        }
        List<String> roleNameList = da.getRolesById(user.getUserId());
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNameList != null) {
            for (String role : roleNameList) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }
        return new
                org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(),
                grantList);
    }

}
