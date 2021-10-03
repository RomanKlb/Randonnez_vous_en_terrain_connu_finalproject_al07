package fr.isika.projetfinal.microservice_user.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.isika.projetfinal.microservice_user.dao.UserRepository;
import fr.isika.projetfinal.microservice_user.model.TypeRole;
import fr.isika.projetfinal.microservice_user.model.User;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService{


	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(!user.isPresent()) {
			throw new UsernameNotFoundException("User email "+email+" not found");
		}
		return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), getGrantedAuthorities(user.get()));
	}

	private Collection<GrantedAuthority> getGrantedAuthorities(User user) {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
		if(user.getRole().getTypeRole().equals(TypeRole.ADMINISTRATOR)) {
			grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}
		grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return grantedAuthorities;
	}

}
