package com.ha.mapper;

import com.ha.pojos.Stuntask;
import com.ha.pojos.StuntaskExample;
import com.ha.pojos.StuntaskKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface StuntaskMapper {
    int countByExample(StuntaskExample example);

    int deleteByExample(StuntaskExample example);

    int deleteByPrimaryKey(StuntaskKey key);

    int insert(Stuntask record);

    int insertSelective(Stuntask record);

    List<Stuntask> selectByExample(StuntaskExample example);

    Stuntask selectByPrimaryKey(StuntaskKey key);

    int updateByExampleSelective(@Param("record") Stuntask record, @Param("example") StuntaskExample example);

    int updateByExample(@Param("record") Stuntask record, @Param("example") StuntaskExample example);

    int updateByPrimaryKeySelective(Stuntask record);

    int updateByPrimaryKey(Stuntask record);
}