package com.ha.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ha.pojos.Student;
import com.ha.pojos.StunclassKey;
import com.ha.pojos.Stuntask;
import com.ha.pojos.Task;
import com.ha.service.StudentService;
import com.ha.service.TaskService;

@Controller
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping("/newTask")
	public void newTask(Task task) {  //布置作业
		List<Integer> i = new ArrayList<Integer>();
		taskService.publishTask(task);
		List<StunclassKey> list = studentService.getStuByCid(task.getCid());
		for(StunclassKey sck : list) {
			i.add(sck.getMid());
		}
		taskService.combStu(task.getTid(), i);
	}
	
	@RequestMapping("/getTask")
	@ResponseBody
	public List<Task> getTask(int cid) {
		List<Task> list = taskService.getTask(cid);
		return list;
	}
	
	@RequestMapping("/getStatistics")
	@ResponseBody
	public Task getStatistics(int tid) {    //统计分析 
		List<Stuntask> list = taskService.findStuByTid(tid);
		Task task = taskService.getTaskByTid(tid);
		Map map = new HashMap();
		for(Stuntask st : list) {
			Student stu = studentService.getStuByMid(st.getMid());
			map.put(stu.getName(), st.getState());
		}
		task.setStatistics(map);
		return task;
	}
}
