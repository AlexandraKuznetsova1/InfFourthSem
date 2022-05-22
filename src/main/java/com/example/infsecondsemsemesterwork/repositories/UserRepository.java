package com.example.infsecondsemsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infsecondsemsemesterwork.models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByEmail(String email);
	Optional<User> findByNick(String email);
	List<User> findAllByNickContains(String nick);
	Optional<User> findByConfirmCodeLike(String code);
}
