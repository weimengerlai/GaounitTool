package com.xuejungao.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;


@Repository
public interface LoginMapperDao {


    // 通过条件查询 数据
    Map<String,String> findLoginByName(@Param("exesql") String exesql);
}
