package com.ha.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ha.mapper.MyclassMapper;
import com.ha.mapper.NoticeMapper;
import com.ha.mapper.StunclassMapper;
import com.ha.pojos.Myclass;
import com.ha.pojos.MyclassExample;
import com.ha.pojos.Notice;
import com.ha.pojos.NoticeExample;
import com.ha.pojos.StudentExample;
import com.ha.pojos.StunclassExample;
import com.ha.pojos.StunclassKey;
import com.ha.service.ClassService;

@Service
@Transactional
public class ClassServiceImpl implements ClassService {

	@Autowired
	private MyclassMapper myclassMapper;
	
	@Autowired
	private StunclassMapper stunclassMapper;
	
	@Autowired
	private NoticeMapper noticeMapper;
	
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

	@Override
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
	}

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
	}

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

}
