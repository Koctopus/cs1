package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.DataBaseMan2;
import com.example.demo.service.DataBaseMan2Service;

@Controller
public class DataBaseManController{
	
	@Autowired
	DataBaseMan2Service databaseman2Service;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/drop")
	public String drop(){
		return "drop";
	}
	
	/*@RequestMapping(value = "/learn")
	public String learn(Model model){
		
		List<Map<String, Object>> ex_data_list = null;
		ex_data_list = jdbcTemplate.queryForList("SELECT * FROM ex_Data5 where name=?","ricola");
		String ex_name = ex_data_list.get(0).get("name").toString();
		String ex_comment = ex_data_list.get(0).get("comment").toString();
		String image = ex_data_list.get(0).get("image").toString();
		
		model.addAttribute("ex_name_data",ex_name);
		model.addAttribute("image_data",image);
		model.addAttribute("ex_comment_data",ex_comment);
		
		return "learn";
	}
	
	@RequestMapping(value = "/graph")
	public String graph(){
		return "graph";
	}*/
	
}
