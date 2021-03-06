package com.ha.service;

import java.util.List;

import com.ha.pojos.Stuntask;
import com.ha.pojos.Task;

public interface TaskService {
	
	public void publishTask(Task task);
	
	public List<Task> getTask(int cid);
	
	public void combStu(int tid,List<Integer> mid);
	
	public List<Stuntask> findStuByTid(int tid);
	
	public List<Stuntask> getStateByMid(int mid);
	
	public Task getTaskByTid(int tid);
	
	public void setConn(int mid,List<Task> list);
	
	public void updateState(int tid,int mid);
}
