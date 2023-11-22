package ca.sheridancollege.menegonj.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ca.sheridancollege.menegonj.database.DatabaseAccess;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	

	@Autowired
	@Lazy
	private DatabaseAccess da;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
		//find the user based on the username (read email)
//		ca.sheridancollege.BookstoreAssignment.menegonj.beans.User user = da.findUserAccount(userName);
//		ca.sheridancollege.menegonj.beans.User user = da.findUserAccount(userName);
		//If the user doesn't exist throw exception
		User user = da.findUserAccount(userName);
		if (user == null) {
			 System.out.println("User not found:" + userName);
			 throw new UsernameNotFoundException("User " + userName + " was not found in the database");
			 }
			 // Get a list of roles for that user
			 List<String> roleNameList = da.getRolesById(Long.valueOf(user.getUsername()));
			System.out.println(roleNameList);
			 // Change the list of the user's roles into a list of GrantedAuthority
			 List<GrantedAuthority> grantList = new ArrayList<>();
			 if (roleNameList != null) {
			 for (String role : roleNameList) {
			 		grantList.add(new SimpleGrantedAuthority(role));
			 	}
			 }
			 // Convert custom User bean into Spring Boot UserDetails
			return new org.springframework.security.core.userdetails.User(
					user.getUsername(), user.getPassword(), grantList);
    }
		

}
