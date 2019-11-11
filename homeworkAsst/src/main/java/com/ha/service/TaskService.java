package com.ha.service;

import java.util.List;

import com.ha.pojos.Stuntask;
import com.ha.pojos.Task;

public interface TaskService {
	
	public void publishTask(Task task);
	
	public List<Task> getTask(int cid);
	
	public void combStu(int tid,List<Integer> mid);
	
	public List<Stuntask> findStuByTid(int tid);
	
	public Task getTaskByTid(int tid);
}
