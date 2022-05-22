package com.example.infsecondsemsemesterwork.repositories;

import com.example.infsecondsemsemesterwork.models.Tip;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.infsecondsemsemesterwork.models.Tip;

import java.util.List;

public interface TipRepository extends JpaRepository<Tip, Integer> {
	List<Tip> findAllByTitleContains(String title);
}
