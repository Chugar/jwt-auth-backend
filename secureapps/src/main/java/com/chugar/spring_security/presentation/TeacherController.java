package com.chugar.spring_security.presentation;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chugar.spring_security.entities.Teacher;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	@GetMapping
	public List<Teacher> getTeachers() {
		List<Teacher> teachers = Arrays.asList(
				new Teacher("l'Oncle", "Rachid", "Cours: Maths"),
				new Teacher("Vladimir", "Volnovski", "Cours: Piano")
			);
					
			return teachers;
	}
	
	@PostMapping
	public void postTeacher(@RequestBody Teacher teacher) {
		System.out.println(teacher.getLastname() + " " + teacher.getFirstname());
	}

}
