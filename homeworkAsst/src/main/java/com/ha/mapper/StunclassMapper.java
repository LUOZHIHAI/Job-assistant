package com.ha.mapper;

import com.ha.pojos.StunclassExample;
import com.ha.pojos.StunclassKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StunclassMapper {
    int countByExample(StunclassExample example);

    int deleteByExample(StunclassExample example);

    int deleteByPrimaryKey(StunclassKey key);

    int insert(StunclassKey record);

    int insertSelective(StunclassKey record);

    List<StunclassKey> selectByExample(StunclassExample example);

    int updateByExampleSelective(@Param("record") StunclassKey record, @Param("example") StunclassExample example);

    int updateByExample(@Param("record") StunclassKey record, @Param("example") StunclassExample example);
    
    List<StunclassKey> self_selectJoinedClass(Integer mid);
}