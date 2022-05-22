package com.example.infsecondsemsemesterwork.repositories;

import com.example.infsecondsemsemesterwork.models.Secret;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SecretRepository extends JpaRepository<Secret, Integer> {
	List<Secret> findAllByTitleContains(String title);
}
