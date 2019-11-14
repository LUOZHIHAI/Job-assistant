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
import com.ha.pojos.Task;
import com.ha.service.ClassService;
import com.ha.service.TaskService;

@Controller
@RequestMapping("/class")
public class ClassController {
	
	@Autowired
	private ClassService classService;
	
	@Autowired
	private TaskService taskService;

	@RequestMapping("/create")
	@ResponseBody
	public String createClass(Myclass myclass) {  //创建班级、同时创建班级公告
		if(myclass.getClassNum()!=null) {
			classService.addClass(myclass);
			classService.joinClass(myclass.getMaster(), myclass.getCid());
			System.out.print(myclass.getCid());
			Notice notice = new Notice();
			notice.setCid(myclass.getCid());
			notice.setTime("1970-01-01");
			classService.newNotice(notice);
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
	
	@RequestMapping("/getTaskNotice")
	@ResponseBody
	public List getTaskAndNotice(int cid,int mid) {	//班级公告、作业
		List<Notice> noticeList = classService.findNotice(cid);
		List<Task> taskList = taskService.getTask(cid);
		taskService.setConn(mid,taskList);
		List list = new ArrayList();
		list.add(noticeList);
		list.add(taskList);
		System.out.print(list.get(0));
		System.out.print(list.get(1));
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
	public List<Myclass> getAllClass(int mid){  //获取相关的所有班级
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
	public List<Myclass> getMyClass(int mid) {  //获取创建的班级
		List<Myclass> list = classService.findMyClass(mid);
		return list;
	}
	
	@RequestMapping("/splitAllClass")
	@ResponseBody
	public List splitAllClass(int mid) {  //分别获取加入的和创建的班级
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
	public void updateName(int cid, String name) {
		Myclass myclass = classService.findClassByCid(cid);
		classService.updateClass(myclass);
		
	}
	
	@RequestMapping("/deleteClass")
	@ResponseBody
	public void deleteClass(int cid) {  	//学委删除班级
		classService.delClass(cid);
	}
	
	@RequestMapping("/exitClass")
	public void exitClass(int mid, int cid) {		//学生退出班级
		classService.exitClass(mid, cid);
	}
}
