package com.example.demo;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class RegisterForm {

    @Pattern(regexp="^\\w{3,32}$", message="size must be between 3 and 32, each character must be alphanumeric or underscore (A-Za-z0-9_)")
    private String username;

    @Size(min=8, max=255)
    private String password;

    private boolean editorcheck;
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getEditorcheck() {
        return editorcheck;
    }

    public void setEditorcheck(boolean editorcheck) {
    	System.out.println(editorcheck);
        this.editorcheck = editorcheck;
    }

}