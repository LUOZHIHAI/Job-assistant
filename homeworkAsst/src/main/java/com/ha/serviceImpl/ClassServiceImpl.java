package com.ha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ha.mapper.MyclassMapper;
import com.ha.mapper.NoticeMapper;
import com.ha.mapper.StunclassMapper;
import com.ha.mapper.StuntaskMapper;
import com.ha.mapper.TaskMapper;
import com.ha.pojos.Myclass;
import com.ha.pojos.MyclassExample;
import com.ha.pojos.Notice;
import com.ha.pojos.NoticeExample;
import com.ha.pojos.StudentExample;
import com.ha.pojos.StunclassExample;
import com.ha.pojos.StunclassKey;
import com.ha.pojos.StuntaskExample;
import com.ha.pojos.Task;
import com.ha.pojos.TaskExample;
import com.ha.service.ClassService;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

	@Autowired
	private MyclassMapper myclassMapper;
	
	@Autowired
	private StunclassMapper stunclassMapper;
	
	@Autowired
	private StuntaskMapper stuntaskMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
	@Autowired
	private TaskMapper taskMapper;
	
	@Override
	public void addClass(Myclass myclass) {
		
		myclassMapper.insert(myclass);
	}

	@Override
	public List<StunclassKey> findClassByMid(int mid) {
		
		StunclassExample example = new StunclassExample();
		StunclassExample.Criteria criteria = example.createCriteria();
		criteria.andMidEqualTo(mid);
		List<StunclassKey> list = stunclassMapper.selectByExample(example);
		return list;
	}


	@Override
	public Myclass findClassByCid(int cid) {
		Myclass myclass = myclassMapper.selectByPrimaryKey(cid);
		return myclass;
	}

/*	@Override
	public Myclass findClassByName(String college, String grade, String major, String school, int classNum) {
		MyclassExample example = new MyclassExample();
		MyclassExample.Criteria criteria = example.createCriteria();
		criteria.andCollegeEqualTo(college);
		criteria.andGradeEqualTo(grade);
		criteria.andMajorEqualTo(major);
		criteria.andSchoolEqualTo(school);
		criteria.andClassNumEqualTo(classNum);
		List<Myclass> list = myclassMapper.selectByExample(example);
		if(!list.isEmpty())
			return list.get(0);
		return null;
	}*/

	/*	暂时废弃
	@Override			
	public Myclass findClassByLongName(String name) {
		MyclassExample example = new MyclassExample();
		List<Myclass> list = myclassMapper.selectByExample(example);
		if(!list.isEmpty())
			for(Myclass myclass : list) {
				String str = myclass.getSchool()+myclass.getCollege()+myclass.getGrade()
								+myclass.getMajor()+myclass.getClassNum()+"班";
				if(name.equals(str)) {
					return myclass;
				}
			}
		return null;
	}*/

	@Override
	public void joinClass(int mid,int cid) {
		StunclassKey sc = new StunclassKey();
		sc.setCid(cid);
		sc.setMid(mid);
		stunclassMapper.insert(sc);
		
	}

	@Override
	public List<Notice> findNotice(int cid) {
		NoticeExample example = new NoticeExample();
		NoticeExample.Criteria criteria = example.createCriteria();
		criteria.andCidEqualTo(cid);
		List<Notice> list = noticeMapper.selectByExample(example);
		return list;
	}

	@Override
	public boolean isMaster(int cid, int mid) {
		Myclass myclass = myclassMapper.selectByPrimaryKey(cid);
		if(myclass.getMaster() == mid)
			return true;
		return false;
	}

	@Override
	public void updateClassNotice(Notice notice) {
		noticeMapper.updateByPrimaryKey(notice);
	}

	@Override
	public List<Myclass> findMyClass(int mid) {
		List<Myclass> list = myclassMapper.slef_selectByMaster(mid);
		return list;
	}

	@Override
	public List<StunclassKey> findJoinedClass(int mid) {
		List<StunclassKey> list = stunclassMapper.self_selectJoinedClass(mid);
		return list;
	}

	@Override
	public void delClass(int cid) {
		//删除学生与班级关联
		StunclassExample stunclassExample = new StunclassExample();
		StunclassExample.Criteria criteria1 = stunclassExample.createCriteria();
		criteria1.andCidEqualTo(cid);
		stunclassMapper.deleteByExample(stunclassExample);
		
		//删除作业、学生作业关联
		TaskExample taskExample = new TaskExample();
		TaskExample.Criteria criteria2 = taskExample.createCriteria();
		criteria2.andCidEqualTo(cid);
		List<Task> list = taskMapper.selectByExample(taskExample);
		for(Task task : list) {
			StuntaskExample sntExamle = new StuntaskExample();
			StuntaskExample.Criteria sntCriteria = sntExamle.createCriteria();
			sntCriteria.andTidEqualTo(task.getTid());
			stuntaskMapper.deleteByExample(sntExamle);
			taskMapper.deleteByPrimaryKey(task.getTid());
		}
		
		//删除班级公告
		NoticeExample noticeExample = new NoticeExample();
		NoticeExample.Criteria criteria4 = noticeExample.createCriteria();
		criteria4.andCidEqualTo(cid);
		noticeMapper.deleteByExample(noticeExample);
		
		//删除班级
		myclassMapper.deleteByPrimaryKey(cid);
	}

	@Override
	public void exitClass(int mid, int cid) {
		//删除学生班级关联
		StunclassKey key = new StunclassKey();
		key.setCid(cid);
		key.setMid(mid);
		stunclassMapper.deleteByPrimaryKey(key);
	}

	@Override
	public void newNotice(Notice notice) {
		noticeMapper.insert(notice);
	}

	@Override
	public void updateClass(Myclass myclass) {
		myclassMapper.updateByPrimaryKey(myclass);
		
	}

}
