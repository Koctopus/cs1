package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.UploadForm;
import com.example.demo.model.DataBaseMan2;
import com.example.demo.service.DataBaseMan2Service;


@Controller
public class UploadFormController {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value="/upload_edit", method = RequestMethod.POST)
	public void upload_edit(HttpSession session, 
            		   		HttpServletResponse response,
            		   		UploadForm form,
            		   		MultipartFile file,
            		   		String ex_name,
            		   		String ex_comment,
            		   		String formula,
            		   		Model model,
            		   		Principal principal) throws Exception
	{
		
		String username_insert = principal.getName();
		
		StringBuffer image_data = new StringBuffer();
		
        InputStream is = file.getInputStream();
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] indata = new byte[10240*16];
        int siz;
        
        while( ( siz = is.read(indata, 0, indata.length) ) > 0 ) {
            os.write( indata, 0, siz );
        }
        is.close();
        
        String base64 = new String(Base64.encodeBase64(os.toByteArray()), "ASCII");

        image_data.append("data:image/jpeg;base64,");
        image_data.append(base64);
        
		jdbcTemplate.update("insert into ex_Data5(username,name,image,comment,formula) VALUES (?,?,?,?,?)",username_insert, ex_name,image_data.toString(),ex_comment,formula);
		
	}

}
