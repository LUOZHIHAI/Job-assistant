package com.ha.service;

import java.util.List;

import com.ha.pojos.Myclass;
import com.ha.pojos.Notice;
import com.ha.pojos.StunclassKey;

public interface ClassService {

	public void addClass(Myclass myclass);
	
	public List<StunclassKey> findClassByMid(int mid);
	
	public Myclass findClassByCid(int cid);
	
	public Myclass findClassByName(String college,String grade,String major,
			String school,int classNum);
	
	public Myclass findClassByLongName(String name);
	
	public void joinClass(int mid,int cid);
	
	public List<Notice> findNotice(int cid);
}
