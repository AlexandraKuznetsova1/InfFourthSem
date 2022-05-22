package com.example.infsecondsemsemesterwork.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infsecondsemsemesterwork.models.TipComment;

import java.util.List;

public interface TipCommentRepository extends JpaRepository<TipComment, Integer> {
	List<TipComment> findAllByTipId(Integer id);
}
