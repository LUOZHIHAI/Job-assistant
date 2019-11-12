package com.ha.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ha.pojos.Myclass;
import com.ha.pojos.Notice;
import com.ha.pojos.StunclassKey;
import com.ha.service.ClassService;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	private ClassService classService;

	@RequestMapping("/create")
	@ResponseBody
	public String createClass(Myclass myclass) {
		if(myclass.getClassNum()!=null) {
			classService.addClass(myclass);
			classService.joinClass(myclass.getMaster(), myclass.getCid());
			return myclass.getCid().toString();
		}
		return "error";
	}
	
	@RequestMapping("/join")
	@ResponseBody
	public String joinClass(int classId,String secretKey,int mid) {  //加入班级
		Myclass myclass = classService.findClassByCid(classId);
		if(myclass != null) {
			if(secretKey.equals(myclass.getSecretKey())) {
				classService.joinClass(mid, myclass.getCid());
				return "ok";
			}
		}
		return "error";
	}
	
	@RequestMapping("/notice")
	@ResponseBody
	public List<Notice> getClassNotice(int cid) {	//班级公告
		List<Notice> list = classService.findNotice(cid);
		return list;
	}
	
	@RequestMapping("/updateNotice")
	@ResponseBody
	public String updateNotice(int cid, int mid, Notice notice) {	// 编辑公告
		if(classService.isMaster(cid, mid)) {
			classService.updateClassNotice(notice);
			return "ok";
		}

		return "error";
	}
	
	@RequestMapping("/getAllClass")
	@ResponseBody
	public List<Myclass> getAllClass(int mid){
		List<Myclass> list = new ArrayList<Myclass>();
		List<StunclassKey>list1 = classService.findClassByMid(mid);
		Myclass myclass;
		if(!list1.isEmpty()) {
			for(StunclassKey sck : list1) {
				 myclass=classService.findClassByCid(sck.getCid());
				 list.add(myclass);
			}
		}
		return list;
	}
	
	@RequestMapping("/getMyClass")
	@ResponseBody
	public List<Myclass> getMyClass(int mid) {
		List<Myclass> list = classService.findMyClass(mid);
		return list;
	}
	
	@RequestMapping("/splitAllClass")
	@ResponseBody
	public List splitAllClass(int mid) {
		Myclass myclass;
		List list = new ArrayList();
		List<Myclass> list1 = classService.findMyClass(mid);
		List<StunclassKey>list2 = classService.findJoinedClass(mid);
		List<Myclass> list3 = new ArrayList<Myclass>();
		if(!list2.isEmpty()) {
			for(StunclassKey sck : list2) {
				 myclass=classService.findClassByCid(sck.getCid());
				 list3.add(myclass);
			}
		}
		list.add(list1);
		list.add(list3);
		return list;
	}
	
	@RequestMapping("/updateClassName")
	@ResponseBody
	public String updateName(int cid, int mid) {
		if(classService.isMaster(cid, mid)) {
			
		}
		return "error";
	}
	
	@RequestMapping("/deleteClass")
	@ResponseBody
	public void deleteClass(int cid) {
		
	}
	
	@RequestMapping("/exitClass")
	public void exitClass(int mid, int cid) {
		classService.exitClass(mid, cid);
	}
}
