package com.ha.mapper;

import com.ha.pojos.Myclass;
import com.ha.pojos.MyclassExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyclassMapper {
    int countByExample(MyclassExample example);

    int deleteByExample(MyclassExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Myclass record);

    int insertSelective(Myclass record);

    List<Myclass> selectByExample(MyclassExample example);

    Myclass selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Myclass record, @Param("example") MyclassExample example);

    int updateByExample(@Param("record") Myclass record, @Param("example") MyclassExample example);

    int updateByPrimaryKeySelective(Myclass record);

    int updateByPrimaryKey(Myclass record);
    
    List<Myclass> slef_selectByMaster(Integer mid);
}