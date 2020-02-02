package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.RegisterForm;
import com.example.demo.model.DataBaseMan2;
import com.example.demo.model.User;
import com.example.demo.service.DataBaseMan2Service;
import com.example.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.validation.BindingResult;

@Controller
public class AuthController {

	@Autowired
    UserService userService;
	
	@Autowired
	DataBaseMan2Service databaseman2Service;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

    @GetMapping("/register")
    public String signup(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(Model model, @Valid RegisterForm registerForm, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
        	System.out.println(bindingResult);
            return "register";
        }

        try {
        	if(registerForm.getEditorcheck()) {
        		System.out.println("editor_true");
        		userService.registerEditor(registerForm.getUsername(), registerForm.getPassword(),registerForm.getEditorcheck());
        	}
        	else{
        		System.out.println("editor_false");
        		userService.registerLearner(registerForm.getUsername(), registerForm.getPassword(),registerForm.getEditorcheck());
        	}
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("registerError", true);
            return "register";
        }
        try {
            request.login(registerForm.getUsername(), registerForm.getPassword());
        } catch (ServletException e) {
            e.printStackTrace();
        }

        return "start";
    }
    
    @GetMapping("/")
    public String login() {
    	return "login";
    }
    
    @PostMapping("/")
    public String loginPost() {
        return "redirect:/login_error";
    }
    
    @RequestMapping("/start")
    public String start() {
    	return "start";
    }
    
    
    /*@PostMapping(value = "/which")
	public String which(Model model,Principal principal){
    	String set="";
    	String username = principal.getName();
    	String userdata = principal.toString();
    	
    	String[] userauth=userdata.split(";");
    	String[] userauth2=userauth[5].split(": ");
    	
    	System.out.println(username);
    	System.out.println(userauth2[1]);
    	
    	if(userauth2[1]=="ROLE_EDITOR") {
    		System.out.println(userauth2[1]);
    		set="edit";
    	}
    	else if(userauth2[1]=="ROLE_LEARNER") {
    		System.out.println(userauth2[1]);
    		set="openfile";
    	}
    	return set;
	}*/
    
    @RequestMapping(value = "/edit")
	public String edit(){
		return "edit";
	}
    
    @RequestMapping(value = "/learn")
	public String learn(){
		return "learn";
	}
    
    @GetMapping(value = "/openfile")
    public String displayList(Model model) {
      List<DataBaseMan2> exlist = databaseman2Service.findAllExData();
      model.addAttribute("exlist", exlist);
      return "openfile";
    }
    
    @GetMapping(value = "/openfile_editor")
    public String displayList2(Model model,Principal principal) {
    	String username = principal.getName();
    	List<DataBaseMan2> exlist = databaseman2Service.findExDataListByUsername(username);
    	model.addAttribute("exlist", exlist);
    	return "openfile_editor";
    }
    
    @GetMapping("/openfile/{id}")
    public String data2learn(@PathVariable Integer id, Model model) {
    	System.out.println("----------"+id);
    	List<Map<String, Object>> ex_data_list = null;
		ex_data_list = jdbcTemplate.queryForList("SELECT * FROM ex_Data5 where id=?",id);
		String ex_name = ex_data_list.get(0).get("name").toString();
		String ex_comment = ex_data_list.get(0).get("comment").toString();
		String image = ex_data_list.get(0).get("image").toString();
		String formula = ex_data_list.get(0).get("formula").toString();
		
		model.addAttribute("ex_name_data",ex_name);
		model.addAttribute("image_data",image);
		model.addAttribute("ex_comment_data",ex_comment);
		model.addAttribute("formula_data",formula);
		
		return "learn";
    }
    
    @GetMapping("/openfile_editor/{id}")
    public String deletefile(@PathVariable Integer id, Model model) {
    	jdbcTemplate.update("delete from ex_Data4 where id=?",id);
    	return "openfile_editor";
    }
    
    @GetMapping("/login_error")
    public String login_error() {
    	return "login_error";
    }
    
}