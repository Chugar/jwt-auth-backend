package com.chugar.spring_security.entities;



public class Student {
	
	private String firstname;
	private String lastname;
	private String degre;
	
	
	
	
	public Student() {

	}


	public Student(String firstname, String lastname, String degre) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.degre = degre;
	}


	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getDegre() {
		return degre;
	}


	public void setDegre(String degre) {
		this.degre = degre;
	}
	
	
	
	
	

}
