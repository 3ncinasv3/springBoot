package ca.sheridancollege.encinasv.security;//package ca.sheridancollege.encinasv.security;


import ca.sheridancollege.encinasv.database.DatabaseAccess;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Service
//public class UserDetailsServiceImpl implements UserDetailsService
//    @Autowired
//    private DatabaseAccess da;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws
//            UsernameNotFoundException {
//// Find the user based on the username (read email)ca.sheridancollege.<yourUserName!!!>.beans.
//User user = da.findUserAccount(username);
//// If the user doesn't exist, throw an exception
//        if (user == null) {
//            System.out.println("User not found:" + username);
//            throw new UsernameNotFoundException("User " + username + "was not found in the database");
//        }
//// Get a list of roles for that user
//        List<String> roleNameList = da.getRolesById(user.getUserId());
//// Change the list of the user's roles into a list of GrantedAuthority
//        List<GrantedAuthority> grantList = new ArrayList<>();
//        if (roleNameList != null) {
//            for (String role : roleNameList) {
//                grantList.add(new SimpleGrantedAuthority(role));
//            }
//        }
//// Convert custom User bean into Spring Boot UserDetails
//        UserDetails userDetails = new
//                org.springframework.security.core.userdetails.User(
//                user.getEmail(), user.getEncryptedPassword(),
//                grantList);
//        return userDetails;
//    }


//}
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final DatabaseAccess da;

    public UserDetailsServiceImpl(DatabaseAccess da) {
        this.da = da;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws
            UsernameNotFoundException {
// Find the user based on the username (read email)
 ca.sheridancollege.encinasv.beans.User user =  da.findUserAccount(username);
// If the user doesn't exist, throw an exception
        if (user == null) {
            System.out.println("User not found:" + username);
            throw new UsernameNotFoundException("User " + username + " was not found in the database");
        }
// Get a list of roles for that user
        List<String> roleNameList = da.getRolesById(user.getUserId());
// Change the list of the user's roles into a list of GrantedAuthority
        List<GrantedAuthority> grantList = new ArrayList<>();
        if (roleNameList != null) {
            for (String role : roleNameList) {
                grantList.add(new SimpleGrantedAuthority(role));
            }
        }
// Convert custom User bean into Spring Boot UserDetails
        UserDetails userDetails = new
                org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getEncryptedPassword(),
                grantList);
        return userDetails;
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
}