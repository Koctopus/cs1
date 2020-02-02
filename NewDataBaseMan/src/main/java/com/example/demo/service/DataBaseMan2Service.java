package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.DataBaseMan2;
import com.example.demo.repository.DataBaseMan2Repository;

@Service
@Transactional
public class DataBaseMan2Service{
	
	@Autowired
	DataBaseMan2Repository databaseman2Repository;
	
	/**
	 * レコードを全件取得する。
	 * @return
	 */
	public List<DataBaseMan2> findAllExData(){
		
		return databaseman2Repository.findAll();
	}
	
	public List<DataBaseMan2> findExDataListByUsername(String username){
		
		return databaseman2Repository.findByUsername(username);
	}
}