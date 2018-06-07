package com.csc.betapp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.csc.betapp.model.AuthenticationUser;
import com.csc.betapp.model.User;

@Service("authenticationService")
@Transactional(readOnly = true)
public class AuthenticationService implements UserDetailsService {
	@Autowired
	private AuthenticationDao authenticationDao;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		List<User> users = authenticationDao.getUserByUserName(userName);
		if (users != null && users.size() > 0) {
			
			List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
			gas.add(new SimpleGrantedAuthority("ROLE_USER"));
			if ("ROLE_ADMIN".equals(users.get(0).getRole())) {
				gas.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			}
			if ("ROLE_SITE_ADMIN".equals(users.get(0).getRole())) {
				gas.add(new SimpleGrantedAuthority("ROLE_SITE_ADMIN"));
			}
			AuthenticationUser aUser = new AuthenticationUser(users.get(0), gas);
			return aUser;
		} else {
			throw new UsernameNotFoundException("Not Found");
		}
	}

}
