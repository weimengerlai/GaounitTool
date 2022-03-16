package com.xuejungao.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;


@Repository
public interface LoginMapperDao {


    // 通过条件查询 数据
    List<Map> findLoginByName(@Param("exesql") String exesql);

    List<Map> findLoginSql(@Param("sql") String sql);

}
