package com.ha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ha.mapper.StudentMapper;
import com.ha.mapper.StunclassMapper;
import com.ha.pojos.StudentExample;
import com.ha.pojos.StunclassExample;
import com.ha.pojos.StunclassKey;
import com.ha.service.StudentService;
import com.ha.pojos.Myclass;
import com.ha.pojos.Student;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private StunclassMapper stunclassMapper;

	@Override
	public Student getStuByName(String name) {
		StudentExample studentExample = new StudentExample();
		StudentExample.Criteria criteria = studentExample.createCriteria();
		criteria.andUsernameEqualTo(name);
		List<Student> list = studentMapper.selectByExample(studentExample);
		if(list != null && list.size()>0)
			return list.get(0);
		else 
			return null;
	}


	@Override
	public void SaveStu(Student student) {
		studentMapper.insert(student);
	}


	@Override
	public List<StunclassKey> getStuByCid(int Cid) {
		StunclassExample example = new StunclassExample();
		StunclassExample.Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(Cid);
		List<StunclassKey> list = stunclassMapper.selectByExample(example);

		return list;
	}


	@Override
	public Student getStuByMid(int Mid) {
		
		return studentMapper.selectByPrimaryKey(Mid);
		
	}

}
