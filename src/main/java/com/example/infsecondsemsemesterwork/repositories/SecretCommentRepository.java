package com.example.infsecondsemsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infsecondsemsemesterwork.models.SecretComment;

import java.util.List;

public interface SecretCommentRepository extends JpaRepository<SecretComment, Integer> {
	List<SecretComment> findAllBySecretId(Integer secretId);
}
