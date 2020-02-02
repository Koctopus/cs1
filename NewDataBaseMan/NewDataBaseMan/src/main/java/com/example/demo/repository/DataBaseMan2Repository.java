package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DataBaseMan2;

@Repository
public interface DataBaseMan2Repository extends JpaRepository<DataBaseMan2, Integer> {
	
	List<DataBaseMan2> findByUsername(String username);
	
}