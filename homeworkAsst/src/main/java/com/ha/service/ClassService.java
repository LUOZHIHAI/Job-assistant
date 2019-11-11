package com.ha.service;

import java.util.List;

import com.ha.pojos.Myclass;
import com.ha.pojos.Notice;
import com.ha.pojos.StunclassKey;

public interface ClassService {

	public void addClass(Myclass myclass);
	
	public List<StunclassKey> findClassByMid(int mid);
	
	public List<Myclass> findMyClass(int mid);
	
	public Myclass findClassByCid(int cid);
	
/*	public Myclass findClassByName(String college,String grade,String major,
			String school,int classNum);				暂时废弃*/
	
//	public Myclass findClassByLongName(String name);   暂时废弃
	
	public void joinClass(int mid,int cid);
	
	public List<Notice> findNotice(int cid);
	
	public boolean isMaster(int cid, int mid);
	
	public void updateClassNotice(Notice notice);
	
	public List<StunclassKey> findJoinedClass(int mid);
	
	public void delClass(int cid);
	
	public void exitClass(int mid, int cid);
}
