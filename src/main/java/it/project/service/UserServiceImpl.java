package it.project.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.dto.UserLoginRequestDto;
import it.project.dto.UserSignupDto;
import it.project.model.User;
import it.project.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	public UserRepository userRepository;

	@Override
	public boolean userLogin(UserLoginRequestDto userLoginRequestDto) {
		Optional<User> optionalUser = userRepository.findByEmail(userLoginRequestDto.getEmail());
		
		boolean exists = optionalUser.isPresent();
		String encrypted = DigestUtils.sha256Hex(userLoginRequestDto.getPassword());
		String optionalPassword = optionalUser.get().getPassword();
		
		boolean equals = encrypted.equals(optionalPassword);
		
		if(exists && equals) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public void userSignup(UserSignupDto userSignupDto) {
		User user = new User();
		
		user.setFirstname(userSignupDto.getFirstname());
		user.setLastname(userSignupDto.getLastname());
		user.setEmail(userSignupDto.getEmail());
		user.setPassword(DigestUtils.sha256Hex(userSignupDto.getPassword()));
		
		userRepository.save(user);
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		boolean exists = optionalUser.isPresent();
		
		if(exists) {
			return optionalUser.get();
		}
		else {
			return new User();
		}
	}

	@Override
	public User getUserById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		boolean exists = optionalUser.isPresent();
		
		if(exists) {
			return optionalUser.get();
		}
		else {
			return new User();
		}
	}

	@Override
	public List<User> getAllUsers() {
		List<User> userList = (List<User>) userRepository.findAll();
		return userList;
	}

	@Override
	public void userDeleteByEmail(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		
		boolean exists = optionalUser.isPresent()
;
		if(exists) {
			User user = optionalUser.get();
			userRepository.delete(user);
		}
	}
	
	@Override
	public void userDeleteById(int id) {
		Optional<User> optionalUser = userRepository.findById(id);
		
		boolean exists = optionalUser.isPresent()
;
		if(exists) {
			User user = optionalUser.get();
			userRepository.delete(user);
		}
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	
	
}
