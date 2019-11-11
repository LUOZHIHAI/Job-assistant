package com.ha.service;

import java.util.List;

import com.ha.pojos.Student;
import com.ha.pojos.StunclassKey;

public interface StudentService {
	
	public Student getStuByName(String name);
	
	public void SaveStu(Student student);

	public List<StunclassKey> getStuByCid(int Cid);
	
	public Student getStuByMid(int Mid);
	
	public void setStuName(int mid, String name);

}
