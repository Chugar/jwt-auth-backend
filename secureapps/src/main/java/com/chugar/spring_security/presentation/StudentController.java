package com.chugar.spring_security.presentation;

import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chugar.spring_security.entities.Student;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = {"http://localhost:4200"})
public class StudentController {
	
	
	@GetMapping
	public List<Student> getStudents() {
		
		List<Student> students = Arrays.asList(
			new Student("Mahmoud", "Ahmadinejad", "Chimie"),
			new Student("Donald", "Trump", "Hotelerie")
		);
				
		return students;
	}
	
	@PostMapping
	public void postStudent(@RequestBody Student student) {
		System.out.println(student.getFirstname() + " " + student.getLastname());
	}

}
