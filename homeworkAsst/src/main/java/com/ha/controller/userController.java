package com.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ha.pojos.Student;
import com.ha.service.StudentService;

@Controller
@RequestMapping("/user")
public class userController {

	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/getName")
	@ResponseBody
	public Student getStuName(String name) {
		Student student = studentService.getStuByName(name);
		if(student != null) {  
			return student;
		}
		else {
			student = new Student();
			student.setUsername(name);
			studentService.SaveStu(student);
			return student;
		}	
	}
	
	@RequestMapping("/setRealName")
	public void realName(int mid, String name) {
		studentService.setStuName(mid, name);
	}
}
