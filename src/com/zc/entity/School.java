package com.zc.entity;

public class School {
	private int schoolId;
	private String schoolName;
	public School(int schoolId, String schoolName) {
		super();
		this.schoolId = schoolId;
		this.schoolName = schoolName;
	}
	public School() {
		// TODO Auto-generated constructor stub
	}
	public int getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(int schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
}
