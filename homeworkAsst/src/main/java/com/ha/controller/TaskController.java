package com.ha.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ha.pojos.Student;
import com.ha.pojos.StunclassKey;
import com.ha.pojos.Stuntask;
import com.ha.pojos.Task;
import com.ha.service.StudentService;
import com.ha.service.TaskService;

import com.ha.utils.ZipUtil;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/newTask")
	@ResponseBody
	public String newTask(Task task) {  //布置作业
		try {
			String path = "I:\\file\\"+task.getCid()+"-"+task.getSubject()+"\\"+task.getTitle();
			File dir = new File(path);
			if (!dir.exists()) {
				System.out.print("a");
		        dir.mkdirs();
			}
			task.setFilepath(path);
			List<Integer> i = new ArrayList<Integer>();
			taskService.publishTask(task);
			List<StunclassKey> list = studentService.getStuByCid(task.getCid());
			for(StunclassKey sck : list) {
				i.add(sck.getMid());
			}
			taskService.combStu(task.getTid(), i);        
	    
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping("/getTask")
	@ResponseBody
	public List<Task> getTask(int cid) {
		List<Task> list = taskService.getTask(cid);
		return list;
	}
	
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Map getStatistics(int tid) {    //统计分析 
		List<Stuntask> list = taskService.findStuByTid(tid);
		Map map = new HashMap();
		for(Stuntask st : list) {
			Student stu = studentService.getStuByMid(st.getMid());
			map.put(stu.getUsername(), st.getState());
		}
		return map;
	}
	
	@RequestMapping("/myPage")
	@ResponseBody
	public List<Integer> myPage(int mid) {    		//个人页面，已完成和未完成的作业统计
		List<Integer> l = new ArrayList<Integer>();
		int n=0,y=0;
		List<Stuntask> list =  taskService.getStateByMid(mid);
		for(Stuntask snt : list) {
			if(snt.getState()==0)
				n++;
			else y++;
		}
		l.add(n);
		l.add(y);
		return l;
	}
	
	@RequestMapping(value="/upload")		//上传文件
	@ResponseBody
	public String upload(@RequestParam int mid,@RequestParam int tid,@RequestParam MultipartFile[] files) {
		try {
			Task task = taskService.getTaskByTid(tid);
			Student stu = studentService.getStuByMid(mid);
			String path = task.getFilepath()+"\\"+stu.getMid()+"-"+stu.getUsername();
			File dir = new File(path);
			if (!dir.exists()) {
		        dir.mkdirs();
			}
			for(MultipartFile file : files) {
				File f = new File(path+"/"+file.getOriginalFilename());
				file.transferTo(f);
			}
			taskService.updateState(tid, mid);
			
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping("/pack")
	@ResponseBody
	public String packFiles(int tid,int mid) {  		//打包作业
		String finalPath = null;
		Task task = taskService.getTaskByTid(tid);
		//作业文件夹目录
		String path = task.getFilepath();  		
		//作业压缩包路径
		String zipFilePath = "I:\\file\\"+task.getCid()+"-"+task.getSubject()+
				"/"+task.getTitle()+".zip";
		try {
			ZipUtil.createZip(path ,zipFilePath,false);
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
		finalPath = "/"+task.getCid()+"-"+task.getSubject()+
				"/"+task.getTitle()+".zip";
		return finalPath;
	}
}
