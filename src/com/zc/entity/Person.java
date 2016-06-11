package com.zc.entity;

public class Person {
	private int personId;
	private String personName;
	private int personAge;
	private int schoolId;
	public Person(){
		
	}
	public Person(int personId,String personName,int personAge,int schoolId){
		this.personId=personId;
		this.personName=personName;
		this.personAge=personAge;
		this.schoolId=schoolId;
	}
	
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public int getPersonAge() {
		return personAge;
	}
	public void setPersonAge(int personAge) {
		this.personAge = personAge;
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	
}
