package guru.springframework.sfgrestbrewery.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import guru.springframework.sfgrestbrewery.model.ERole;
import guru.springframework.sfgrestbrewery.model.User;
import guru.springframework.sfgrestbrewery.repository.UserRepository;
import guru.springframework.sfgrestbrewery.security.services.UserDetailsImpl;

@Service
@Slf4j
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	public void save (User user){
		if(getUserById(user.getId()).isPresent())
			userRepository.save(user);
		else
			log.info("user not present in db");
	}
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userRepository.getUserByUsername(username);
		
		return UserDetailsImpl.build(user);
	}
	
	public Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public void deleteUserById(Integer roleId) {
		userRepository.deleteById(roleId);
	}
	
	public Optional<User> getUserById(Integer userId) {
		return userRepository.findById(userId);
	}
	
	public List<User> getUserByRole(ERole role){
		return userRepository.getUserByRole(role.name());
	}

}
