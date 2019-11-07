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
			return "ok";
		}
		return "error";
	}
	
	@RequestMapping("/join")
	@ResponseBody
	public String joinClass(String className,String secretKey,int mid) {  //加入班级
		if(className != null) {
			Myclass myclass = classService.findClassByLongName(className);
			if(myclass != null) {
				if(secretKey.equals(myclass.getSecretKey())) {
					classService.joinClass(mid, myclass.getCid());
					return "ok";
				}
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
	
	@RequestMapping("/getClass")
	@ResponseBody
	public List<Myclass> getClass(int mid){
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
	
	
	
}
