package com.ha.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ha.mapper.StuntaskMapper;
import com.ha.mapper.TaskMapper;
import com.ha.pojos.MyclassExample;
import com.ha.pojos.Notice;
import com.ha.pojos.NoticeExample;
import com.ha.pojos.Stuntask;
import com.ha.pojos.StuntaskExample;
import com.ha.pojos.StuntaskKey;
import com.ha.pojos.Task;
import com.ha.pojos.TaskExample;
import com.ha.service.TaskService;

@Service
@Transactional
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskMapper taskMapper;
	
	@Autowired
	private StuntaskMapper stuntaskMapper;
	
	@Override
	public void publishTask(Task task) {
		taskMapper.insert(task);
	}

	@Override
	public List<Task> getTask(int cid) {
		TaskExample example = new TaskExample();
		TaskExample.Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);
		List<Task> list = taskMapper.selectByExample(example);
		return list;
	}

	@Override
	public void combStu(int tid, List<Integer> mid) {
		Stuntask stuntask = new Stuntask();
		byte state = 0;
		for(int i = 0; i<mid.size(); i++) {
			stuntask.setTid(tid);
			stuntask.setMid(mid.get(i));
			stuntask.setState(state);
			stuntaskMapper.insert(stuntask);
		}
		
	}

	@Override
	public List<Stuntask> findStuByTid(int tid) {
		StuntaskExample example = new StuntaskExample();
		StuntaskExample.Criteria criteria = example.createCriteria();
		criteria.andTidEqualTo(tid);
		List<Stuntask> list = stuntaskMapper.selectByExample(example);
		return list;
	}

	@Override
	public Task getTaskByTid(int tid) {
		Task task = taskMapper.selectByPrimaryKey(tid);
		return task;
	}

	@Override
	public void setConn(int mid,List<Task> list) {
		for(Task task : list) {
			StuntaskKey key= new StuntaskKey();
			key.setMid(mid);
			key.setTid(task.getTid());
			Stuntask snt = stuntaskMapper.selectByPrimaryKey(key);	
			task.setPerState(snt.getState());
		}
		
	}

	@Override
	public void updateState(int tid, int mid) {
		Stuntask snt = new Stuntask();
		snt.setMid(mid);
		snt.setTid(tid);
		snt.setState(new Integer(1).byteValue());
		stuntaskMapper.updateByPrimaryKey(snt);
	}

	@Override
	public List<Stuntask> getStateByMid(int mid) {
		StuntaskExample example = new StuntaskExample();
		StuntaskExample.Criteria criteria = example.createCriteria();
		criteria.andMidEqualTo(mid);
		List<Stuntask> list = stuntaskMapper.selectByExample(example);
		return list;
	}

	
}
