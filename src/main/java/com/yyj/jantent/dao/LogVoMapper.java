package com.yyj.jantent.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import com.yyj.jantent.modal.vo.LogVo;
import com.yyj.jantent.modal.vo.LogVoExample;

import java.util.List;

@Component
public interface LogVoMapper {
    long countByExample(LogVoExample example);

    int deleteByExample(LogVoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(LogVo record);

    int insertSelective(LogVo record);

    List<LogVo> selectByExample(LogVoExample example);

    LogVo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") LogVo record, @Param("example") LogVoExample example);

    int updateByExample(@Param("record") LogVo record, @Param("example") LogVoExample example);

    int updateByPrimaryKeySelective(LogVo record);

    int updateByPrimaryKey(LogVo record);
}