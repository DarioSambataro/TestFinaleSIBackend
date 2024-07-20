package it.project.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;


import it.project.model.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findByEmail(String email);
	Optional<User> findById(int id);
	
	boolean existsByEmail(String email);
	
}
