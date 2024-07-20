package it.project.service;

import java.util.List;

import it.project.dto.UserLoginRequestDto;
import it.project.dto.UserSignupDto;
import it.project.model.User;

public interface UserService {
	boolean userLogin(UserLoginRequestDto userLoginRequestDto);
	void userSignup(UserSignupDto userSignupDto);
	User getUserByEmail(String email);
	User getUserById(int id);
	List<User> getAllUsers();
	void userDeleteByEmail(String email);
	void userDeleteById(int id);
	boolean existsUserByEmail(String email);
}
